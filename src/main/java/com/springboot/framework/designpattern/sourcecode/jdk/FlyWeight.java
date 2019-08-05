package com.springboot.framework.designpattern.sourcecode.jdk;

/**
 * 享元模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 0:03
 */
public class FlyWeight {
    public static void main(String[] args) {
        // 如果Integer.valueOf(x) x 在 -128 --- 127 之间，就是使用享元模式返回，
        // 如果不在范围内，则仍然 new

        // 小结：
        // 1.在valueOf 方法中，先判断是否在IntegerCache 中，如果不在，就创建新的Integer(new)，否则，就直接从缓冲池中返回
        // 2.valueOf 方法，就使用到享元模式
        // 3.如果使用valueOf 方法得到一个Integer 实例，范围在 -128 --- 127 之间，执行速度比 new 快

        // 得到 x实例，类型Integer
        Integer x = Integer.valueOf(127);
        // 得到 y实例，类型Integer
        Integer y = new Integer(127);
        // ...
        Integer z = Integer.valueOf(127);
        // ...
        Integer w = new Integer(127);

        // 大小，true
        System.out.println(x.equals(y));
        // false
        System.out.println(x == y);
        // true
        System.out.println(x == z);
        // false
        System.out.println(w == x);
        // false
        System.out.println(w == y);
    }
}
