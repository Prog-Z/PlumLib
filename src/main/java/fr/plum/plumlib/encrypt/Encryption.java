package fr.plum.plumlib.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {
    
    public static String SHA1(String word) {
        return DigestUtils.sha512Hex(word);
    }
}