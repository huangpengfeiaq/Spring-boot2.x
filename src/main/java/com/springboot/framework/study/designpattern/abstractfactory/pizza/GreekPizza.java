package com.springboot.framework.study.designpattern.abstractfactory.pizza;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:29
 */
public class GreekPizza extends BasePizza {
    @Override
    public void prepare() {
        setName("希腊披萨");
        System.out.println(" 给制作希腊披萨 准备原材料");
    }
}