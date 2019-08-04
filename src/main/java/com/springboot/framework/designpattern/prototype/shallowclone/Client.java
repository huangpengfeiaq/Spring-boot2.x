package com.springboot.framework.designpattern.prototype.shallowclone;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 10:21
 */
public class Client {
    public static void main(String[] args) {
        // 传统的方法
        Sheep sheep1 = new Sheep("tom", 1, "白色");
        sheep1.setFriend(new Sheep("jack", 2, "黑色"));
        Sheep sheep2 = new Sheep(sheep1.getName(), sheep1.getAge(), sheep1.getColor());
        Sheep sheep3 = (Sheep) sheep1.clone();
        Sheep sheep4 = (Sheep) sheep1.clone();
        Sheep sheep5 = (Sheep) sheep1.clone();
        Sheep sheep6 = (Sheep) sheep1.clone();
        Sheep sheep7 = (Sheep) sheep1.clone();

        System.out.println(sheep1);
        System.out.println(sheep2);
        System.out.println(sheep3);
        System.out.println(sheep4);
        System.out.println(sheep5);
        System.out.println(sheep6);
        System.out.println(sheep7);
        System.out.println(sheep6 == sheep7);
        System.out.println(sheep6.getFriend() == sheep7.getFriend());
    }
}
