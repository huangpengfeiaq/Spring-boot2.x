package com.springboot.framework.study.designpattern.builder;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 15:39
 */
public class Client {
    public static void main(String[] args) {
        AbstractHouse house = new CommonHouse();
        house.build();
    }
}
