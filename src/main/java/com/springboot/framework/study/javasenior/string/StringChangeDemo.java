package com.springboot.framework.study.javasenior.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * String转换
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 16:38
 */
public class StringChangeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str1 = "123";
        int num = Integer.parseInt(str1);
        String str2 = String.valueOf(num);
        String str3 = num + "";

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);

        System.out.println("****************");

        String str4 = "123";
        char[] charArray = str4.toCharArray();
        for (char ch : charArray) {
            System.out.println(ch);
        }

        String str5 = new String(charArray);
        System.out.println(str5);

        System.out.println("****************");

        // 编码：字符串 --> 字节
        // 解码：字节 --> 字符串

        String str6 = "abc123中国";
        // 使用默认的字符集，进行转换
        byte[] bytes = str6.getBytes();
        System.out.println(Arrays.toString(bytes));

        // 使用gbk字符集，进行编码
        byte[] gbks = str6.getBytes("gbk");
        System.out.println(Arrays.toString(gbks));
    }
}
