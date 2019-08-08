package com.springboot.framework.study.designpattern.sourcecode.jdk;

import java.util.Arrays;
import java.util.Comparator;

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
        // 1.实现了 Comparator 接口（策略接口），匿名类 对象 new Comparator<Integer>() {...}
        // 2.对象 new Comparator<Integer>() {...} 就是实现了 策略接口 的对象
        // 3.public int compare(Integer o1, Integer o2) {...} 指定具体的处理方式
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        // 说明
        // public static <T> void sort(T[] a, Comparator<? super T> c) {
        //        if (c == null) {
        //            sort(a);
        //        } else {
        //            if (LegacyMergeSort.userRequested)
        //                legacyMergeSort(a, c); //使用策略对象c
        //            else //使用策略对象c
        //                TimSort.sort(a, 0, a.length, c, null, 0, 0);
        //        }
        //    }
        Arrays.sort(data, comparator);

        System.out.println(Arrays.toString(data));
    }
}
