package com.springboot.framework.designpattern.singleton.type2;

/**
 * 单例模式（饿汉式-静态代码块）
 * 未实现懒加载，可能会出现内存浪费
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:08
 */
public class SingletonTest02 {
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
 * 饿汉式（静态代码块）
 */
class Singleton {
    /**
     * 1.构造器私有化，外部不能new
     */
    private Singleton() {
    }

    /**
     * 2.本类内部创建对象实例
     */
    private static Singleton instance;

    // 在静态代码块中，创建单例对象
    static {
        instance = new Singleton();
    }

    /**
     * 3.提供一个共有的静态方法，返回实例对象
     */
    public static Singleton getInstance() {
        return instance;
    }
}