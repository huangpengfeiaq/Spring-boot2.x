package com.springboot.framework.designpattern.factory.simplefactory.pizzastore.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:29
 */
public class GreekPizza extends AbstractPizza {
    @Override
    public void prepare() {
        System.out.println(" 给制作希腊披萨 准备原材料");
    }
}