package com.kotelking.shorten.config;

import com.kotelking.shorten.repository.DbRepository;
import com.kotelking.shorten.repository.LocalRepository;
import com.kotelking.shorten.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

@ComponentScan
@Configuration
public class RepositoryConfig {

    public static final String REPOSITORY_LIST="RepositoryList";

    public static final String LOCAL = "localRepository";
    public static final String DB = "dbRepository";
    public static final String URL_REPOSITORY = "urlRepository"; //use this repository

    @Bean
    @Qualifier(LOCAL)
    public UrlRepository localRepository() {
        return new LocalRepository();
    }

    @Bean
    @Qualifier(DB)
    public UrlRepository dbRepository() {
        return new DbRepository();
    }

    @Bean
    @Qualifier(URL_REPOSITORY)
    public UrlRepository urlRepository(@Qualifier(LOCAL) UrlRepository repository) {

        return repository;

    }

    @Bean
    @Qualifier(REPOSITORY_LIST)
    public List<UrlRepository> repositories(
            @Qualifier(LOCAL) UrlRepository local,
            @Qualifier(DB) UrlRepository db){
        List<UrlRepository> registerRepositories=new ArrayList<>();
        registerRepositories.add(local);
        registerRepositories.add(db);
        return Collections.unmodifiableList(registerRepositories);
    }

}
