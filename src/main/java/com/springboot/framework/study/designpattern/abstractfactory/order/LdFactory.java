package com.springboot.framework.study.designpattern.abstractfactory.order;

import com.springboot.framework.study.designpattern.abstractfactory.pizza.BasePizza;
import com.springboot.framework.study.designpattern.abstractfactory.pizza.CheesePizza;
import com.springboot.framework.study.designpattern.abstractfactory.pizza.GreekPizza;
import com.springboot.framework.study.designpattern.abstractfactory.pizza.PepperPizza;

/**
 * 这是工厂子类
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 0:15
 */
public class LdFactory implements AbstractFactory {
    @Override
    public BasePizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        BasePizza pizza = null;
        switch (orderType) {
            case "greek":
                pizza = new GreekPizza();
                break;
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "pepper":
                pizza = new PepperPizza();
                break;
            default:
                break;
        }
        return pizza;
    }
}
