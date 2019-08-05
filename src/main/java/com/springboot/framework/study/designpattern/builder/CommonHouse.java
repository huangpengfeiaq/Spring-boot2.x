package com.springboot.framework.study.designpattern.builder;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 15:38
 */
public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子打砌墙");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子封顶");
    }
}
