package com.springboot.framework.study.designpattern.singleton.type7;

/**
 * 单例模式（静态内部类）
 * 推荐使用
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:08
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

/**
 * 静态内部类,推荐使用
 */
class Singleton {
    /**
     * 1.构造器私有化，外部不能new
     */
    private Singleton() {
    }

    /**
     * 2.写一个静态内部类，该类中有一个静态属性 Singleton
     */
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    /**
     * 3.提供一个共有的静态方法，直接返回SingletonInstance.INSTANCE
     */
    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}