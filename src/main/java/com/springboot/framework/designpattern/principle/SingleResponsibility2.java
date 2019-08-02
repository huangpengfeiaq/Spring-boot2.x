package com.springboot.framework.designpattern.principle;

/**
 * 单一职责原则
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/2 20:53
 */
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        WaterVehicle waterVehicle = new WaterVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}

/**
 * 交通工具类
 * 方式2 的分析
 * 1.遵守单一职责原则
 * 2.但是这样做的改动很大，即将类分解，同时修改客户端
 * 3.改进：直接修改Vehicle 类，改动的代码会比较少 => 方案3
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行。。。");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在天空中运行。。。");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在水中运行。。。");
    }
}