package com.springboot.framework.designpattern.principle;

/**
 * 单一职责原则
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/2 20:53
 */
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle = new Vehicle2();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.runAir("飞机");
    }
}

/**
 * 交通工具类
 * 方式3
 */
class Vehicle2 {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行。。。");
    }
    public void runAir(String vehicle) {
        System.out.println(vehicle + " 在天空中运行。。。");
    }
    public void runWater(String vehicle) {
        System.out.println(vehicle + " 在水中运行。。。");
    }
}