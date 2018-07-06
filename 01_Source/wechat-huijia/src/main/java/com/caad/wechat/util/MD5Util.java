package com.caad.wechat.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class MD5Util {

    public static String format(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for (byte x : digest) {
            if ((x & 0xff) >> 4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
}
