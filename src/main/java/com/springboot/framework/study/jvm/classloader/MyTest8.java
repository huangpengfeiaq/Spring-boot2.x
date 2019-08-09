package com.springboot.framework.study.jvm.classloader;

/**
 * 不同的类加载器作用与类加载器动作分析
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/9 21:45
 */
public class MyTest8 {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader);

        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}