package com.springboot.framework.designpattern.bridge;

/**
 * 直立式样式手机
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/5 1:26
 */
public class UpRightPhone extends Phone {
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println(" 直立式样式手机");
    }

    @Override
    public void close() {
        super.close();
        System.out.println(" 直立式样式手机");
    }

    @Override
    public void call() {
        super.call();
        System.out.println(" 直立式样式手机");
    }
}
