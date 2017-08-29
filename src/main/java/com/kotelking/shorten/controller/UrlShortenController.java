package com.kotelking.shorten.controller;

import com.kotelking.shorten.config.ShortenerConfig;
import com.kotelking.shorten.exception.ApiException;
import com.kotelking.shorten.shortener.UrlShortener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("shortUrl")
public class UrlShortenController {


    private final UrlShortener urlShortener;

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenController.class);

    @Autowired
    public UrlShortenController(@Qualifier(ShortenerConfig.SHORTNER) UrlShortener urlShortener){

        this.urlShortener=urlShortener;
    }

    @RequestMapping(method= RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
    public String createUrl(@RequestBody String url){

        UriComponentsBuilder builder;
        try {
             builder= UriComponentsBuilder.fromHttpUrl(url);
        }catch(IllegalArgumentException e){
            throw new ApiException(e,HttpStatus.BAD_REQUEST);
        }

        String shortUrl=urlShortener.shorten(builder.build().toUriString());
        LOGGER.debug("short Url : {}",shortUrl);
        return shortUrl;
    }

    @RequestMapping(method= RequestMethod.GET,value="{url}")
    public ResponseEntity<Void> getUrl(@PathVariable("url") String url){

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setLocation(URI.create(urlShortener.revert(url)));
        ResponseEntity<Void> redirect=new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
        return redirect;

    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> exception(ApiException e){

        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }
}
