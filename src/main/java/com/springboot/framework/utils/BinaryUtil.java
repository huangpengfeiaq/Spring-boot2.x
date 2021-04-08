package com.springboot.framework.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangpengfei
 * @since 2019/7/16
 */
public class BinaryUtil {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public BinaryUtil() {
    }

    public static String toBase64String(byte[] binaryData) {
        return new String(Base64.encodeBase64(binaryData));
    }

    public static byte[] fromBase64String(String base64String) {
        return Base64.decodeBase64(base64String);
    }

    public static byte[] calculateMd5(byte[] binaryData) {
        MessageDigest messageDigest = null;

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("MD5 algorithm not found.");
        }

        // 使用指定的字节更新摘要
        messageDigest.update(binaryData);
        // 获得密文
        return messageDigest.digest();
    }

    public static String encodeMd5(String s) {
        byte[] binaryData = s.getBytes();

        byte[] md5Bytes = calculateMd5(binaryData);
        // 把密文转换成十六进制的字符串形式
        int len = md5Bytes.length;
        char[] buf = new char[len * 2];

        int k = 0;
        for (int i = 0; i < len; i++) {
            buf[k++] = HEX_DIGITS[md5Bytes[i] >>> 4 & 0xf];
            buf[k++] = HEX_DIGITS[md5Bytes[i] & 0xf];
        }
//        for(int i = 0; i < len; ++i) {
//            buf[i * 2] = HEX_DIGITS[md5Bytes[i] >>> 4 & 15];
//            buf[i * 2 + 1] = HEX_DIGITS[md5Bytes[i] & 15];
//        }

        return new String(buf);
    }

}