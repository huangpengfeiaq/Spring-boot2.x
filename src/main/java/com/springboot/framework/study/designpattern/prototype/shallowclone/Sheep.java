package com.springboot.framework.study.designpattern.prototype.shallowclone;

import lombok.Data;

/**
 * 原型模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 10:20
 */
@Data
public class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;
    private String address = "蒙古羊";
    private Sheep friend;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    /**
     * 克隆该实例，使用默认的clone方法来完成
     *
     * @return Sheep
     */
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }
}
