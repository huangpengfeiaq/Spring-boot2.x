package com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 16:02
 */
public class PepperPizza extends AbstractPizza {
    @Override
    public void prepare() {
        System.out.println(" 给胡椒披萨 准备原材料");
    }
}
