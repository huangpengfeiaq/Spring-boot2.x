package com.springboot.framework.designpattern.builder.improve;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 16:05
 */
public class HouseDirector {
    HouseBuilder houseBuilder = null;

    /**
     * 1.构造器传入
     *
     * @param houseBuilder houseBuilder
     */
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    /**
     * 2.setter方法
     *
     * @param houseBuilder houseBuilder
     */
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    /**
     * 如何建造房子的过程，交给指挥者
     */
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
