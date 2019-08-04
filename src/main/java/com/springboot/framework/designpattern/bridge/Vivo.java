package com.springboot.framework.designpattern.bridge;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/5 1:15
 */
public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo手机开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
