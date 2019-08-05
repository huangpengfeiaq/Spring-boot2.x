package com.springboot.framework.study.designpattern.builder;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 15:36
 */
public abstract class AbstractHouse {
    /**
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

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}
