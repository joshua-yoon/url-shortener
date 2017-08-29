package com.kotelking.shorten.repository;

import com.kotelking.shorten.config.DBConfig;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DbRepository implements UrlRepository {

    private SqlSession sqlSession;


    @Autowired
    public void setSession(@Qualifier(DBConfig.DBTargets.SHORTURL) SqlSession sqlSession){
        this.sqlSession =sqlSession;
    }

    private SqlSession getSession(){
        return sqlSession;
    }

    @Override
    public String set(String shorten, String original) {

        Map<String,String> urlMap=new HashMap<>();
        urlMap.put("short",shorten);
        urlMap.put("original",original);

        getSession().insert("setUrl",urlMap);
        return shorten;
    }

    @Override
    public String get(String shorten) {

        return getSession().selectOne("getShort",shorten);
    }

    @Override
    public String findOriginal(String original) {
        return getSession().selectOne("findOriginal",original);
    }

    @Override
    public int size() {
        return getSession().selectOne("getSize");
    }
}
