package com.springboot.framework.designpattern.factory.simplefactory.pizzastore.order;

import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.AbstractPizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.CheesePizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.GreekPizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.PepperPizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:31
 */
public class OrderPizza {
    /**
     * 构造器
     */
    public OrderPizza() {
        AbstractPizza pizza = null;
        // 订购披萨的类型
        String orderType;
        do {
            orderType = getType();
            if ("greek".equals(orderType)) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if ("cheese".equals(orderType)) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            } else if ("pepper".equals(orderType)) {
                pizza = new PepperPizza();
                pizza.setName("胡椒披萨");
            } else {
                break;
            }
            // 输出pizza 制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    /**
     * 写一个方法，可以获取客户端希望订购的披萨种类
     */
    private String getType() {
        try {
            BufferedReader string = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type:");
            return string.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
