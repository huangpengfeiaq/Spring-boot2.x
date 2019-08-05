package com.springboot.framework.study.designpattern.singleton.type5;

/**
 * 单例模式（懒汉式-线程不安全，同步代码块）
 * 不能使用！！！
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:35
 */
public class SingletonTest05 {
    public static void main(String[] args) {
        // 测试
        System.out.println("懒汉式3-线程不安全，同步代码块~");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}