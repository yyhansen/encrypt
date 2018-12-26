package aes.util;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA {

    public static String sha1(byte[] contents){
        return DigestUtils.sha1Hex(contents);
    }

    public static String sha256(byte[] contents){
        return DigestUtils.sha256Hex(contents);
    }

    public static String sha512(byte[] contents){
        return DigestUtils.sha512Hex(contents);
    }
}
