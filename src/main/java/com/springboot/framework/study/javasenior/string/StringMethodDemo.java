package com.springboot.framework.study.javasenior.string;

/**
 * String类的方法
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 15:53
 */
public class StringMethodDemo {
    public static void main(String[] args) {
        String s1 = "helloWorld";

        System.out.println(s1.length());
        System.out.println(s1.charAt(0));
        System.out.println(s1.charAt(9));
//        s1 = "";
        System.out.println(s1.isEmpty());

        String s2 = s1.toUpperCase();
        // s1不可变的，仍然为原来的字符串
        System.out.println(s1);
        // s2改成大写以后的字符串
        System.out.println(s2);

        String s3 = "  he  ll o  World ";
        // trim()去除首尾空格
        String s4 = s3.trim();
        System.out.println("---" + s3 + "---");
        System.out.println("---" + s4 + "---");

        System.out.println("***************************");

        String s5 = "abc";
        String s6 = s5.concat("def");
        System.out.println(s5);
        System.out.println(s6);

        String s7 = "abc";
        String s8 = new String("abe");
        // 涉及到字符串排序
        System.out.println(s7.compareTo(s8));

        String s9 = "黄鹏飞先生";
        String s10 = s9.substring(1, 3);
        System.out.println(s10);

        System.out.println("***************************");

        String s11 = "黄鹏飞先生.hpf";
        boolean b1 = s11.endsWith(".hpf");
        System.out.println(b1);

        boolean b2 = s11.startsWith("黄");
        boolean b3 = s11.startsWith("鹏飞", 1);
        System.out.println(b2);
        System.out.println(b3);

        // 判断是否存在
        boolean b4 = s11.contains("先生");
        System.out.println(b4);
    }
}
