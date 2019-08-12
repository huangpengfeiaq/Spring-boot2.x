package com.springboot.framework.study.javasenior.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 23:46
 */
public class WindowTest3 {
    public static void main(String[] args) {
        Window3 window3 = new Window3();

        Thread h1 = new Thread(window3);
        Thread h2 = new Thread(window3);
        Thread h3 = new Thread(window3);

        h1.setName("窗口1");
        h2.setName("窗口2");
        h3.setName("窗口3");

        h1.start();
        h2.start();
        h3.start();
    }
}

class Window3 implements Runnable {
    private int ticket = 100;
    // 1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // 2.调用锁定方法：lock()
                lock.lock();

                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 3.调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}
