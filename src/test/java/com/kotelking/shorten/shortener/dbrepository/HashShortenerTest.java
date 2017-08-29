package com.kotelking.shorten.shortener.dbrepository;

import com.kotelking.shorten.repository.DbRepository;
import com.kotelking.shorten.repository.LocalRepository;
import com.kotelking.shorten.shortener.HashShortener;
import com.kotelking.shorten.shortener.MockSession;
import com.kotelking.shorten.shortener.ShortenerTest;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;


public class HashShortenerTest extends ShortenerTest {


    @Before
    public void prepare(){

        SqlSession session=new MockSession();
        DbRepository dbRepository= new DbRepository();
        dbRepository.setSession(session);

        urlShortener=new HashShortener(dbRepository);
    }
}
