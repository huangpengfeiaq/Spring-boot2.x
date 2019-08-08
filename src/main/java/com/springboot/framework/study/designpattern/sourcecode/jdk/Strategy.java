package com.springboot.framework.study.designpattern.sourcecode.jdk;

/**
 * 策略模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/8 12:13
 */
public class Strategy {
    public static void main(String[] args) {
        // 数组
        Integer[] data = {9, 1, 2, 8, 4, 3};

        // 实现升序排序，返回-1放左边，1放右边，0保存不变
        // 说明
        // 1.实现了 Comparable 接口
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };
    }
}
