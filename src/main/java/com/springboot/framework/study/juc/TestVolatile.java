package com.springboot.framework.study.juc;

import lombok.Data;

/**
 * 一、volatile 关键字
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/12 12:27
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("-------------");
                break;
            }
        }
    }
}

@Data
class ThreadDemo implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag=" + isFlag());

    }
}