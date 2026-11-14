package com.xx.blbl.network;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Util {
    public static String md5(String str) {
        try {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            return toHex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b5 : bArr) {
            sb.append(Character.forDigit((b5 & 240) >> 4, 16));
            sb.append(Character.forDigit(b5 & 15, 16));
        }
        return sb.toString();
    }
}
