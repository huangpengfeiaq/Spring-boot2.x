package com.springboot.framework.designpattern.sourcecode.jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * 组合模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/5 13:31
 */
public class Composite {
    public static void main(String[] args) {
        // 说明
        // 1.Map 就是一个抽象的构建（类似我们的Component）
        // 2.HashMap是一个中间的构建（Composite），实现/继承了相关的方法（put，putAll）
        // 3.Node 是 HashMap的静态内部类，类似Leaf叶子节点，这里没有put，putAll
        // static class Node<K,V> implements Map.Entity<K,V>

        Map<Integer, String> hashMap = new HashMap<>(16);
        // 直接存放叶子节点（Node）
        hashMap.put(0, "东游记");

        Map<Integer, String> map = new HashMap<>(16);
        map.put(1, "西游记");
        map.put(2, "红楼梦");
        hashMap.putAll(map);

        System.out.println(hashMap);
    }
}
