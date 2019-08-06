package com.springboot.framework.study.designpattern.templatemethod;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 10:19
 */
public class PeanutSoybeanMilk extends AbstractSoybeanMilk {
    @Override
    void addCondiments() {
        System.out.println("第三步：加入上好的花生");
    }
}
