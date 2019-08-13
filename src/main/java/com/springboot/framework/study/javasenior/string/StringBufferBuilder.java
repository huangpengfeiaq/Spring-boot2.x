package com.springboot.framework.study.javasenior.string;

/**
 * StringBuffer
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 17:04
 */
public class StringBufferBuilder {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("abc");
        stringBuffer.append(4);
        stringBuffer.append("4");
        System.out.println(stringBuffer.length());
        System.out.println(stringBuffer);
    }
}
