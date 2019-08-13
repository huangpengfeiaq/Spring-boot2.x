package com.springboot.framework.study.javasenior.thread;

/**
 * 例子：创建三个窗口卖票，总票数为100张
 * 存在线程的安全问题，待解决
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 20:21
 */
public class WindowDemo {
    public static void main(String[] args) {
        Window h1 = new Window();
        Window h2 = new Window();
        Window h3 = new Window();

        h1.setName("窗口1");
        h2.setName("窗口2");
        h3.setName("窗口3");

        h1.start();
        h2.start();
        h3.start();
    }
}

class Window extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (Window.class) {
                if (ticket > 0) {
                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}