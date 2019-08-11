package com.springboot.framework.study.jvm.gc;

import lombok.Data;

/**
 * 垃圾回收算法
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/10 13:55
 */
public class Main {
    private Main instance;

    public Main() {
        byte[] m = new byte[20 * 1024 * 1024];
    }

    public static void main(String[] args) {
        Main m1 = new Main();
        Main m2 = new Main();
        m1.instance = m2;
        m2.instance = m1;

        m1 = null;
        m2 = null;

        System.gc();
    }
}
