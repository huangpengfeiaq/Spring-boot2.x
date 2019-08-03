package com.springboot.framework.designpattern.singleton.type4;

/**
 * 单例模式（懒汉式-线程安全，同步方法）
 * 不推荐使用！！
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:35
 */
public class SingletonTest04 {
    public static void main(String[] args) {
        // 测试
        System.out.println("懒汉式2-线程安全~");
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
     * 提供一个静态公有方法，加入同步处理的代码，解决线程安全问题
     * 即懒汉式
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}