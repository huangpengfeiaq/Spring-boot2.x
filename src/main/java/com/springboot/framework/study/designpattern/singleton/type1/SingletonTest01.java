package com.springboot.framework.study.designpattern.singleton.type1;

/**
 * 单例模式（饿汉式-静态常量）
 * 未实现懒加载，可能会出现内存浪费
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:08
 */
public class SingletonTest01 {
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
 * 饿汉式（静态常量）
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
    private final static Singleton INSTANCE = new Singleton();

    /**
     * 3.提供一个共有的静态方法，返回实例对象
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }
}