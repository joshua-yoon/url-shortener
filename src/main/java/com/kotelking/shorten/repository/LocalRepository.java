package com.kotelking.shorten.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class LocalRepository implements UrlRepository {

    private Map<String,String> storage;

    public LocalRepository(){
        storage=new HashMap<>();
    }

    @Override
    public String set(String shorten, String original) {
        return storage.putIfAbsent(shorten,original);

    }

    @Override
    public String get(String shorten) {
        return storage.get(shorten);
    }

    @Override
    public String findOriginal(String original) {
        Optional<Map.Entry<String,String>> shortUrl=storage.entrySet()
                .stream()
                .filter(entry->entry.getValue().equals(original))
                .findFirst();
        if (shortUrl.isPresent())
            return shortUrl.get().getKey();
        return null;

    }

    @Override
    public int size() {
        return storage.size();
    }
}
