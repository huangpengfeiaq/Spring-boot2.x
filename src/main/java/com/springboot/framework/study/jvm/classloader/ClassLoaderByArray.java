package com.springboot.framework.study.jvm.classloader;

/**
 * 数组的类加载器
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/9 22:11
 */
public class ClassLoaderByArray {
    public static void main(String[] args) {
        Integer[] integers = new Integer[1];
        // 表示Integer所属的类加载器为RootClassLoader，因此返回结果为null
        System.out.println(integers.getClass().getClassLoader());

        ClassLoaderByArray[] classLoaderByArrays = new ClassLoaderByArray[1];
        // 表示ClassLoaderByArray所属的类加载器为AppClassLoader，因此返回结果为sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoaderByArrays.getClass().getClassLoader());

        int[] ints = new int[1];
        // 表示int所属的类加载器为没有，因此返回结果为null（与第一种情况不同）
        System.out.println(ints.getClass().getClassLoader());
    }
}
