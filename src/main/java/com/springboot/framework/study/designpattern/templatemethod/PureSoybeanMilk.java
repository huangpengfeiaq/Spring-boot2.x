package com.springboot.framework.study.designpattern.templatemethod;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 10:26
 */
public class PureSoybeanMilk extends AbstractSoybeanMilk {
    @Override
    void addCondiments() {
        // 空实现
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
