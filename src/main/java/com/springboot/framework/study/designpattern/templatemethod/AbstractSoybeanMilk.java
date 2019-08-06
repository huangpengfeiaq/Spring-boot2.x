package com.springboot.framework.study.designpattern.templatemethod;

/**
 * 抽象类，表示豆浆
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 10:15
 */
public abstract class AbstractSoybeanMilk {
    /**
     * 模板方法，做成final，不让子类去覆盖
     */
    final void make() {
        select();
        if (customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    /**
     * 选材料
     */
    void select() {
        System.out.println("第一步：加入上好的黄豆");
    }

    /**
     * 加入配料
     */
    abstract void addCondiments();

    /**
     * 浸泡
     */
    void soak() {
        System.out.println("第三步：浸泡10分钟");
    }

    /**
     * 品尝
     */
    void beat() {
        System.out.println("第四步：品尝");
    }

    /**
     * 钩子方法，决定是否需要添加配料
     */
    boolean customerWantCondiments() {
        return true;
    }
}
