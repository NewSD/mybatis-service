//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.niu.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final String CHARSET = "UTF-8";

    public MD5() {
    }

    public static String getMD5(String sourceStr) {
        String resultStr = "";

        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            byte[] b = md5.digest();
            char[] digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            byte[] var6 = b;
            int var7 = b.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                byte aB = var6[var8];
                char[] ob = new char[]{digit[aB >>> 4 & 15], digit[aB & 15]};
                resultStr = resultStr + new String(ob);
            }

            return resultStr;
        } catch (NoSuchAlgorithmException var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public static String md5(String text) {
        StringBuilder sb = new StringBuilder();

        try {
            byte[] bytes = text.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            byte[] var4 = bytes;
            int var5 = bytes.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                byte aByte = var4[var6];
                if((aByte & 255) < 16) {
                    sb.append("0");
                }

                sb.append(Long.toString((long)(aByte & 255), 16));
            }
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        return sb.toString().toLowerCase();
    }

    public static boolean verify(String text, String md5) throws Exception {
        String md5Text = md5(text);
        return md5Text.equalsIgnoreCase(md5);
    }
}
