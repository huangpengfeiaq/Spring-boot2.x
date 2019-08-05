package com.springboot.framework.study.designpattern.builder.improve;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 16:04
 */
public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println(" 高楼的地基打100米");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 高楼的墙20米");
    }

    @Override
    public void roofed() {
        System.out.println(" 高楼的屋顶透明");
    }
}