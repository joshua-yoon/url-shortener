package com.kotelking.shorten.repository;

public interface UrlRepository {
    String set(String shorten, String original);
    String get(String shorten);
    String findOriginal(String original);
    int size();
}
