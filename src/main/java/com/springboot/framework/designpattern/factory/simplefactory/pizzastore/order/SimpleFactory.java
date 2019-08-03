package com.springboot.framework.designpattern.factory.simplefactory.pizzastore.order;

import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.AbstractPizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.CheesePizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.GreekPizza;
import com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza.PepperPizza;

/**
 * 简单工厂模式，也叫静态工厂模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 16:08
 */
public class SimpleFactory {
    /**
     * 根据orderType 返回对应的Pizza 对象
     *
     * @param orderType 订单类型
     * @return Pizza对象
     */
    public static AbstractPizza createPizza(String orderType) {
        AbstractPizza pizza = null;
        System.out.println("使用简单工厂模式~");
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}
