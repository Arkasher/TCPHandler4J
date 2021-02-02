package com.yan.tcphandler4j.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class TokenUtils {

    public static byte[] generateToken() {
        byte[] bytes = new byte[10];
        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static boolean compare(byte[] data1, byte[] data2) {
        return Arrays.equals(data1, data2);
    }

}
