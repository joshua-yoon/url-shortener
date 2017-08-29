package com.kotelking.shorten.shortener;

import com.kotelking.shorten.config.RepositoryConfig;
import com.kotelking.shorten.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Base64;

@Component
public class SerialShortener implements UrlShortener{


    private UrlRepository urlRepository;


    @Autowired
    public SerialShortener(@Qualifier(RepositoryConfig.URL_REPOSITORY) UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }

    @Override
    public String shorten(String longUrl) {



        String shortUrl=urlRepository.findOriginal(longUrl);
        if (shortUrl == null) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            byteBuffer.putInt(urlRepository.size());
            shortUrl=Base64.getEncoder()
                    .withoutPadding()
                    .encodeToString(byteBuffer.array());
            urlRepository.set(shortUrl,longUrl);

        }
        return shortUrl;
    }

    @Override
    public String revert(String shortUrl) {
        return urlRepository.get(shortUrl);
    }
}
