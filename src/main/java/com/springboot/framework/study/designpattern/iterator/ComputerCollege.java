package com.springboot.framework.study.designpattern.iterator;

import java.util.Iterator;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 19:00
 */
public class ComputerCollege implements College {
    private Department[] departments;
    private int numOfDepartment = 0;

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("Java 专业", "Java 专业");
        addDepartment("php 专业", "php 专业");
        addDepartment("大数据 专业", "大数据 专业");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment] = department;
        numOfDepartment++;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
