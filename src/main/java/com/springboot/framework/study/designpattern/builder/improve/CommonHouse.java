package com.springboot.framework.study.designpattern.builder.improve;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 16:03
 */
public class CommonHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基5米");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子打砌墙10米");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子盖普通屋顶");
    }
}
