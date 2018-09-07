package com.hfuuacm.Tools;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Encryption {
    public static String MD5BASE64(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        BASE64Encoder base64en = new BASE64Encoder();
        return base64en.encode(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
    }

    public static String RandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 20; i ++)
            stringBuffer.append(str.charAt(random.nextInt(62)));
        return stringBuffer.toString();
    }
}
