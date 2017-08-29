package com.kotelking.shorten.config;

import com.kotelking.shorten.repository.UrlRepository;
import com.kotelking.shorten.shortener.HashShortener;
import com.kotelking.shorten.shortener.SerialShortener;
import com.kotelking.shorten.shortener.UrlShortener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class ShortenerConfig {

    public static final String HASH="hashShortener";
    public static final String SERIAL="serialShortener";
    public static final String SHORTNER="shortner";

    @Bean
    @Qualifier(HASH)
    public UrlShortener hashShortener(@Qualifier(RepositoryConfig.URL_REPOSITORY) UrlRepository repository) {
        return new HashShortener(repository);
    }

    @Bean
    @Qualifier(SERIAL)
    public UrlShortener serialShortener(@Qualifier(RepositoryConfig.URL_REPOSITORY) UrlRepository repository) {
        return new SerialShortener(repository);
    }

    @Bean
    @Qualifier(SHORTNER)
    public UrlShortener shortner(@Qualifier(HASH) UrlShortener urlShortener) {

        return urlShortener;

    }
}
