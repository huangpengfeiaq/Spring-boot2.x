package com.springboot.framework.study.javasenior.thread;

/**
 * 创建多线程的方式二：实现Runnable接口
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 20:35
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();

        Thread thread1 = new Thread(myThread2);
        thread1.setName("线程一");
        thread1.start();

        Thread thread2 = new Thread(myThread2);
        thread2.setName("线程二");
        thread2.start();
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}