package com.kotelking.shorten.util;

import com.kotelking.shorten.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

public class Base64Utils {
    /**
     * RFC4648 Base64 Url Safe
     *
     * Alternative Characters
     *
     * http://www.ietf.org/rfc/rfc3986.txt
     * 2.3.  Unreserved Characters
     * unreserved  = ALPHA / DIGIT / "-" / "." / "_" / "~"
     *
     * http://www.ietf.org/rfc/rfc2396.txt
     * 2.3. Unreserved Characters
     * unreserved  = alphanum |  "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
     */
    private static final char[] base64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
    };

    private static Logger LOGGER = LoggerFactory.getLogger(Base64Utils.class);

    private static HashMap<Character,Integer> charIndex=new HashMap<>();

    static {
        for (int i=0;i<base64.length;++i){
            charIndex.put(base64[i],i);
        }
    }
    public static final String BASE64_FIRST_STRING=String.valueOf(base64[0]);

    /**
     * get Index of Base64 char, integer position
     * same as radix 64 number
     * @param c
     * @return
     */
    public static int toNumber(char c){
        return charIndex.get(c);
    }

    public static char toChar(int index) { return base64[index];}

    /**
     * generate base64 md5 hash string
     * @param str
     * @return
     */
    public static String getMd5(String str) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            LOGGER.error("Cannot find MD5 Message Digest Instance {}",e);
            //Alternative method : getHashString
            throw new ApiException(e);
        }

        md.update(str.getBytes());
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(md.digest());
    }

    /**
     * preserve method
     * @param str
     * @return
     */
    private static String getHashString(String str) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(str.hashCode());

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(byteBuffer.array());
    }


}
