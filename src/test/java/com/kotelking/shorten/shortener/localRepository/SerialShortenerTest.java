package com.kotelking.shorten.shortener.localrepository;

import com.kotelking.shorten.repository.LocalRepository;
import com.kotelking.shorten.shortener.SerialShortener;
import com.kotelking.shorten.shortener.ShortenerTest;
import org.junit.Before;

/**
 * Created by joshua on 2017. 8. 26..
 */
public class SerialShortenerTest extends ShortenerTest {

    @Before
    public void prepare(){

        urlShortener=new SerialShortener(new LocalRepository());
    }
}
