package com.springboot.framework.study.designpattern.abstractfactory.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:28
 */
public class CheesePizza extends BasePizza {
    @Override
    public void prepare() {
        setName("奶酪披萨");
        System.out.println(" 给制作奶酪披萨 准备原材料");
    }
}
