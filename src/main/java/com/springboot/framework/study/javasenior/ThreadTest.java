package com.springboot.framework.study.javasenior;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3.创建Thread类的子类的对象
 * 4.通过此对象调用start()
 * <p>
 * 例子：遍历100以内的所有的偶数
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 17:49
 */
public class ThreadTest {
    public static void main(String[] args) {
        // 3.创建Thread类的子类的对象
        MyThread t1 = new MyThread();
        // 4.通过此对象调用start()： ①启动当前线程 ②调用当前线程的run()
        t1.start();

        // 再启动一个线程
        MyThread t2 = new MyThread();
        t2.start();

        // 如下操作仍然是在main线程中执行的。
        for (int i = 0; i < 100; i++) {
            // “按位与”运算，打印奇数
            if ((i & 1) == 1) {
                System.out.println(Thread.currentThread().getName() + "奇数:" + i);
            }
        }

        // 创建Thread类的匿名子类的对象
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    // “按位与”运算，打印奇数
                    if ((i & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + "奇数:" + i);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    // “按位与”运算，打印奇数
                    if ((i & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + "奇数:" + i);
                    }
                }
            }
        }.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // “取模”运算，打印偶数
            if ((i % 2) == 0) {
                System.out.println(Thread.currentThread().getName() + "偶数:" + i);
            }
        }
    }
}
