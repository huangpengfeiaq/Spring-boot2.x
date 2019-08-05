package com.springboot.framework.study.designpattern.builder.improve;

/**
 * 抽象的建造者
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 16:00
 */
public abstract class HouseBuilder {
    protected House house = new House();

    /**
     * 将建造的流程写好，抽象的方法
     * 打地基
     */
    public abstract void buildBasic();

    /**
     * 砌墙
     */
    public abstract void buildWalls();

    /**
     * 封顶
     */
    public abstract void roofed();

    public House buildHouse() {
        return house;
    }
}
