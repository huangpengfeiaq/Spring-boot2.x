package com.springboot.framework.study.designpattern.singleton.type3;

/**
 * 单例模式（懒汉式-线程不安全）
 * 不推荐使用！！
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:35
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        // 测试
        System.out.println("懒汉式-线程不安全~");
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

    /**
     * 提供一个静态公有方法，当使用到该方法时，才去创建instance
     * 即懒汉式
     */
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}