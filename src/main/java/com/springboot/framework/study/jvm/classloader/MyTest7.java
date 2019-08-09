package com.springboot.framework.study.jvm.classloader;

/**
 * 类加载器双亲委托机制
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/9 21:27
 */
public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.lang.String");
        System.out.println(aClass.getClassLoader());

        Class<?> aClass1 = Class.forName("com.springboot.framework.study.jvm.classloader.C");
        System.out.println(aClass1.getClassLoader());
    }
}

class C {
}