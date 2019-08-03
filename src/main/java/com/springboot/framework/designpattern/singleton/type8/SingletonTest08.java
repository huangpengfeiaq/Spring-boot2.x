package com.springboot.framework.designpattern.singleton.type8;

/**
 * 单例模式（枚举）
 * 推荐使用
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 12:16
 */
public class SingletonTest08 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

        instance.sayOk();
    }
}

enum Singleton {
    /**
     * 属性
     */
    INSTANCE;

    public void sayOk() {
        System.out.println("ok~");
    }
}