package com.springboot.framework.study.designpattern.templatemethod;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 10:21
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("制作花生豆浆~~~~~");
        AbstractSoybeanMilk peanutSoybeanMilk = new PeanutSoybeanMilk();
        peanutSoybeanMilk.make();

        System.out.println("制作红豆豆浆~~~~~");
        AbstractSoybeanMilk redBeanSoybeanMilk = new RedBeanSoybeanMilk();
        redBeanSoybeanMilk.make();

        System.out.println("制作纯豆浆~~~~~");
        AbstractSoybeanMilk pureSoybeanMilk = new PureSoybeanMilk();
        pureSoybeanMilk.make();
    }
}
