package com.springboot.framework.designpattern.abstractfactory.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 16:02
 */
public class PepperPizza extends BasePizza {
    @Override
    public void prepare() {
        setName("胡椒披萨");
        System.out.println(" 给胡椒披萨 准备原材料");
    }
}
