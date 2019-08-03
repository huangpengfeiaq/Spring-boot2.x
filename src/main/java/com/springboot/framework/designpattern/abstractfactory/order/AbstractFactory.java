package com.springboot.framework.designpattern.abstractfactory.order;

import com.springboot.framework.designpattern.abstractfactory.pizza.BasePizza;

/**
 * 抽象工厂模式的抽象层（接口）
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 0:09
 */
public interface AbstractFactory {
    /**
     * 让下面的工厂子类来 具体实现
     *
     * @param orderType 订单类型
     * @return Pizza
     */
    BasePizza createPizza(String orderType);
}
