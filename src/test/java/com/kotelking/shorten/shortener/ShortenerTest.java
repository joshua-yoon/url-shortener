package com.kotelking.shorten.shortener;

import com.kotelking.shorten.repository.LocalRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
        TestConfig.class})
public class ShortenerTest {

    protected UrlShortener urlShortener;

    private static final String TEST_URL="en.wikipedia.org/wiki/URL_shortening";

    private static final String[] TEST_URLS=new String[]{
            "en.wikipedia.org/wiki/URL_shortening",
            "google.com",
            "www.ietf.org",
            "www.facebook.com",
            "mail.google.com",
            "www.naver.com",
            "www.kakaocorp.com/main"

    };

    private static final List<String> URL_LIST= Arrays.asList(TEST_URLS);

    private static Logger LOGGER = LoggerFactory.getLogger(ShortenerTest.class);

    /**
     * prepare Stroage, Local Storage For a test
     */
    @Before
    public void prepare(){
        prepareHashSortenerTest();
    }

    protected void prepareHashSortenerTest(){

        urlShortener=new HashShortener(new LocalRepository());
    }

    protected void prepareSerialShorterTest(){

        urlShortener=new SerialShortener(new LocalRepository());
    }



    /**
     * simple shortenning test
     */
    @Test
    public void shorten(){

        URL_LIST.stream().forEach(url->{
            String shortUrl=urlShortener.shorten(url);
            LOGGER.debug(shortUrl);
            Assert.assertTrue(UrlShortener.URL_LENGTH >= shortUrl.length());

        });


    }

    /**
     * get original url test from generated short url
     */
    @Test
    public void restore(){

        URL_LIST.stream().forEach(url->{
            String shortUrl=urlShortener.shorten(url);
            String longUrl=urlShortener.revert(shortUrl);
            Assert.assertEquals(url,longUrl);
        });

    }

    /**
     * duplicated original url test
     * return same url if original is same
     */
    @Test
    public void duplcation(){
        Map<String,String> urlSave = new HashMap<>();
        URL_LIST.stream().forEach(url->{
            String shortUrl=urlShortener.shorten(url);
            urlSave.put(url,shortUrl);
        });

        Random random = new Random();
        IntStream.range(1,1000).parallel()
                .forEach(i->{
                    int randomIndex=random.nextInt(TEST_URLS.length);
                    String longUrl=TEST_URLS[randomIndex];
                    String shortUrl=urlShortener.shorten(longUrl);
                    String expected=urlSave.get(longUrl);
                    LOGGER.debug("Expected {}, Generated {}", expected,shortUrl);
                    Assert.assertEquals(expected,shortUrl);
                });

    }

}
