package com.springboot.framework.study.designpattern.sourcecode.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 19:28
 */
public class IteratorDemo {
    public static void main(String[] args) {
        // List充当了聚合接口
        List<String> list = new ArrayList<>();
        list.add("jack");
        /// 获取到迭代器
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
