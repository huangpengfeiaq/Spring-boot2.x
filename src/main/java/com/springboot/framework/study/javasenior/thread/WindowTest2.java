package com.springboot.framework.study.javasenior.thread;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 例子：创建三个窗口卖票，总票数为100张（方式二）
 * 存在线程的安全问题，待解决
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 20:44
 */
public class WindowTest2 {
    public static void main(String[] args) {
        Window2 window2 = new Window2();

        Thread h1 = new Thread(window2);
        Thread h2 = new Thread(window2);
        Thread h3 = new Thread(window2);

        h1.setName("窗口1");
        h2.setName("窗口2");
        h3.setName("窗口3");

        h1.start();
        h2.start();
        h3.start();
    }
}

class Window2 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (Window2.class) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
