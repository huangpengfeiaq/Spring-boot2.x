package com.springboot.framework.designpattern.abstractfactory.order;

import com.springboot.framework.designpattern.abstractfactory.pizza.BasePizza;
import com.springboot.framework.designpattern.abstractfactory.pizza.CheesePizza;
import com.springboot.framework.designpattern.abstractfactory.pizza.GreekPizza;
import com.springboot.framework.designpattern.abstractfactory.pizza.PepperPizza;

/**
 * 这是工厂子类
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 0:12
 */
public class BjFactory implements AbstractFactory {
    @Override
    public BasePizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        BasePizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
