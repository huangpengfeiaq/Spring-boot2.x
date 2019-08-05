package com.springboot.framework.study.designpattern.abstractfactory.order;

/**
 * 相当于一个客户端，符出订购任务
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:55
 */
public class PizzaStore {
    public static void main(String[] args) {
        // 使用抽象工厂模式
//        new OrderPizza(new BjFactory());
        new OrderPizza(new LdFactory());

        System.out.println("~退出程序~");
    }
}
