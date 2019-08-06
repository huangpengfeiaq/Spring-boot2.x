package com.springboot.framework.study.designpattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 18:47
 */
public class InfoCollegeIterator implements Iterator {
    /**
     * 这里我们需要知道Department 是以怎样的方式存放的
     * 这里使用List存放
     */
    List<Department> departments;
    /**
     * 索引
     */
    int index = 0;

    public InfoCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }


    /**
     * 判断是否还有下一个元素
     *
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return index < departments.size();
    }

    @Override
    public Object next() {
        Department department = departments.get(index);
        index++;
        return department;
    }

    /**
     * 删除的方法默认空实现
     */
    @Override
    public void remove() {

    }
}
