package com.springboot.framework.study.designpattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 19:11
 */
public class OutPutImpl {
    /**
     * 学院集合
     */
    private List<College> collegeList;

    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    /**
     * 输出 所有学院
     */
    public void printCollege() {
        // 从collegeList 取出所有学院，Java 中的List 已经实现了Iterator
        Iterator iterator = collegeList.iterator();
        while (iterator.hasNext()) {
            // 取出一个学院
            College college = (College) iterator.next();
            System.out.println("==========" + college.getName() + "==========");
            printDepartment(college.createIterator());
        }
//        // 常规写法如下
//        for (College college : collegeList) {
//            // 取出一个学院
//            System.out.println("==========" + college.getName() + "==========");
//            printDepartment(college.createIterator());
//        }
    }

    /**
     * 输出 学院输出系
     */
    private void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department department = (Department) iterator.next();
            System.out.println(department.getName());
        }
    }
}
