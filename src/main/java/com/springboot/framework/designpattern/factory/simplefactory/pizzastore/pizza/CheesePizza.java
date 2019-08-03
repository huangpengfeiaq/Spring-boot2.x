package com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:28
 */
public class CheesePizza extends AbstractPizza {
    @Override
    public void prepare() {
        System.out.println(" 给制作奶酪披萨 准备原材料");
    }
}
