package com.kotelking.shorten.shortener;

import com.kotelking.shorten.config.RepositoryConfig;
import com.kotelking.shorten.exception.ApiException;
import com.kotelking.shorten.repository.UrlRepository;
import com.kotelking.shorten.util.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HashShortener implements UrlShortener {


    // for avoiding hash collision
    private static final int HASH_COLLISION_LIST_LENGTH = 1;
    private static final boolean HASH_COLLISON_AVOID = HASH_COLLISION_LIST_LENGTH > 0;

    private static final int HASH_STRING_LENGTH = URL_LENGTH - HASH_COLLISION_LIST_LENGTH;

    private final UrlRepository storage;


    @Autowired
    public HashShortener(@Qualifier(RepositoryConfig.URL_REPOSITORY) UrlRepository storage){
        this.storage=storage;
    }


    @Override
    public String shorten(String longUrl) {

        String hash= Base64Utils.getMd5(longUrl).substring(0, HASH_STRING_LENGTH);

        String shortUrl;

        if (HASH_COLLISON_AVOID)
            shortUrl=getHashWithIndex(hash,longUrl); //safe but slow
        else
            shortUrl=hash;

        storage.set(shortUrl,longUrl);
        return shortUrl;
    }

    private String makeHashKey(String hash, int index){
        return hash+Base64Utils.toChar(index);
    }

    /**
     * get shorten Url with duplicated index, supporting 64 duplications
     * TODO : support Long index size
     * @param hash
     * @return
     */
    private String getHashWithIndex(String hash,String longUrl){
        String index=storage.get(hash);
        if (index == null) {
            index= Base64Utils.BASE64_FIRST_STRING;
            storage.set(hash,index);
        }else{
            int currentIndex=Base64Utils.toNumber(index.charAt(0));

            for (int i=0;i<currentIndex;i++){
                String key=makeHashKey(hash,i);
                String original=storage.get(key);
                if (longUrl.equals(original))
                    return key;

            }

            int newIndex=currentIndex++;
            if (newIndex > 64-1){
                throw new ApiException("Too many collision, not supported", HttpStatus.BAD_REQUEST);
            }
            String newKey=makeHashKey(hash,newIndex);
            storage.set(newKey,longUrl);
            storage.set(hash,newKey);

        }
        return hash+index;
    }



    @Override
    public String revert(String shortUrl) {

        return storage.get(shortUrl);
    }


}
