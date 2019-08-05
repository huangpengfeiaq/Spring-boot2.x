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
