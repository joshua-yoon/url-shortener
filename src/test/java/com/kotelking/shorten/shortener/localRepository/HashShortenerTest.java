package com.kotelking.shorten.shortener.localrepository;

import com.kotelking.shorten.repository.LocalRepository;
import com.kotelking.shorten.shortener.HashShortener;
import com.kotelking.shorten.shortener.ShortenerTest;
import org.junit.Before;


public class HashShortenerTest extends ShortenerTest {



    @Before
    public void prepare(){

        urlShortener=new HashShortener(new LocalRepository());
    }
}
