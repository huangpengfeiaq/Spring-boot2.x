package com.springboot.framework.designpattern.singleton.type6;

/**
 * 单例模式（双重检查）
 * 推荐使用
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 11:35
 */
public class SingletonTest06 {
    public static void main(String[] args) {
        // 测试
        System.out.println("双重检查~");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }
}

class Singleton {
    /**
     * 把instance声明为volatile型，就可以实现线程安全的延迟初始化。
     * 因为被volatile关键字修饰的变量是被禁止重排序的。
     */
    private static volatile Singleton instance;

    private Singleton() {
    }

    /**
     * 提供一个静态公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     * 即双重检查
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}