package com.springboot.framework.study.javasenior.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式四：使用线程池
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 13:38
 */
public class ThreadPool {
    public static void main(String[] args) {
        // 1.提供指定数量的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        // 2.执行指定的线程的操作
        // 适合适用于Runnable
        pool.execute(new NumberThread1());
        pool.execute(new NumberThread2());
        // 适合适用于Callable
//        service.submit();

        // 3.关闭连接池
        pool.shutdown();
    }
}

class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 1) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }
}

class NumberThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }
}