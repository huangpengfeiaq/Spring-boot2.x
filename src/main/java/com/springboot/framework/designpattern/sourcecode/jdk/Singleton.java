package com.springboot.framework.designpattern.sourcecode.jdk;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 12:21
 */
public class Singleton {
    public static void main(String[] args) {
        // JDK 中源码使用单例模式场景
        // Runtime 使用了饿汉式
        Runtime runtime = Runtime.getRuntime();
    }
}
