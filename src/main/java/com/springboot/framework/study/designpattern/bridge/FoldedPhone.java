package com.springboot.framework.study.designpattern.bridge;

/**
 * 折叠样式手机，继承 抽象类 Phone
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/5 1:17
 */
public class FoldedPhone extends Phone {
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println(" 折叠样式手机");
    }

    @Override
    public void close() {
        super.close();
        System.out.println(" 折叠样式手机");
    }

    @Override
    public void call() {
        super.call();
        System.out.println(" 折叠样式手机");
    }
}
