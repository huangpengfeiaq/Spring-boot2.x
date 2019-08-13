package com.springboot.framework.study.javasenior.string;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 14:29
 */
public class StringDemo {
    public static void main(String[] args) {
        String s0 = new String("231");
        String s1 = new String("231");
        String s2 = "231";
        String s3 = "231";
        System.out.println(s0.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        System.out.println(s0 == s1);
        System.out.println(s1 == s2);
        System.out.println(s2 == s2);

        System.out.println("***************************");

        String s4 = "javaEE";
        String s5 = "hadoop";

        String s6 = "javaEEhadoop";
        String s7 = "javaEE" + "hadoop";
        String s8 = s4 + "hadoop";
        String s9 = "javaEE" + s5;
        String s10 = s4 + s5;
        System.out.println(s6.hashCode());
        System.out.println(s7.hashCode());
        System.out.println(s8.hashCode());
        System.out.println(s9.hashCode());
        System.out.println(s10.hashCode());
        System.out.println(s6 == s7);
        System.out.println(s7 == s8);
        System.out.println(s8 == s9);
        System.out.println(s9 == s10);

        // intern()将返回值指向常量池
        String s11 = s10.intern();
        System.out.println(s11 == s6);
    }
}
