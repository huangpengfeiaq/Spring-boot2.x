package com.springboot.framework.study.designpattern.iterator;

import java.util.Iterator;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 18:55
 */
public interface College {
    /**
     * 返回学院名称
     *
     * @return 学院名称
     */
    String getName();

    /**
     * 增加系的方法
     *
     * @param name 名称
     * @param desc 描述
     */
    void addDepartment(String name, String desc);

    /**
     * 返回一个迭代器，遍历
     *
     * @return 迭代器
     */
    Iterator createIterator();
}
