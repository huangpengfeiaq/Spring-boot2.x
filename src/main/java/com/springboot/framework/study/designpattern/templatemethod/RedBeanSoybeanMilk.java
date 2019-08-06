package com.springboot.framework.study.designpattern.templatemethod;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 10:22
 */
public class RedBeanSoybeanMilk extends AbstractSoybeanMilk {
    @Override
    void addCondiments() {
        System.out.println("第三步：加入上好的红豆");
    }
}
