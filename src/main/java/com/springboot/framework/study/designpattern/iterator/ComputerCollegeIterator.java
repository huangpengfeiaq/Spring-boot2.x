package com.springboot.framework.study.designpattern.iterator;

import java.util.Iterator;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 18:40
 */
public class ComputerCollegeIterator implements Iterator {
    /**
     * 这里我们需要知道Department 是以怎样的方式存放的
     */
    Department[] departments;
    /**
     * 遍历的位置
     */
    int position = 0;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    /**
     * 判断是否还有下一个元素
     *
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return position < departments.length - 1 && departments[position] != null;
//        return position < departments.length && departments[position] != null;
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position++;
        return department;
    }

    /**
     * 删除的方法默认空实现
     */
    @Override
    public void remove() {

    }
}
