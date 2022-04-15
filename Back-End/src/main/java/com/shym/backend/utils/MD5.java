package com.shym.backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final Logger LOG = LoggerFactory.getLogger(MD5.class);

    public static String getMD5Hash(String s) throws IOException, NoSuchAlgorithmException {
        if (s == null) return null;
        return getMD5Hash(new ByteArrayInputStream(s.getBytes()));

    }

    public static String getMD5Hash(InputStream is) throws IOException, NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] byteArray = new byte[1024];
        int bytesCount;

        while ((bytesCount = is.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
