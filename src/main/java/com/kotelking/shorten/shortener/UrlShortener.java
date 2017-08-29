package com.kotelking.shorten.shortener;

public interface UrlShortener {

    int URL_LENGTH=8;

    String shorten(String longUrl);
    String revert(String shortUrl);
}
