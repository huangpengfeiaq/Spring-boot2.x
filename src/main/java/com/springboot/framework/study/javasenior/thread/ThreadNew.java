package com.springboot.framework.study.javasenior.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable接口
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 12:58
 */
public class ThreadNew {
    public static void main(String[] args) throws Exception {
        NumThread numThread = new NumThread();
        FutureTask futureTask = new FutureTask<>(numThread);
        Thread thread = new Thread(futureTask);
        thread.start();

        int val = (int) futureTask.get();
        System.out.println("总和为" + val);
    }
}

class NumThread implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                sum += i;
                System.out.println(i);
            }
        }
        return sum;
    }
}