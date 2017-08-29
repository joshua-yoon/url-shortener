package com.kotelking.shorten.shortener.dbrepository;

import com.kotelking.shorten.repository.DbRepository;
import com.kotelking.shorten.repository.LocalRepository;
import com.kotelking.shorten.shortener.MockSession;
import com.kotelking.shorten.shortener.SerialShortener;
import com.kotelking.shorten.shortener.ShortenerTest;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;

/**
 * Created by joshua on 2017. 8. 26..
 */
public class SerialShortenerTest extends ShortenerTest {

    @Before
    public void prepare(){

        SqlSession session=new MockSession();
        DbRepository dbRepository= new DbRepository();
        dbRepository.setSession(session);

        urlShortener=new SerialShortener(dbRepository);
    }
}
