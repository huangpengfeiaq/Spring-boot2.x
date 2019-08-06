package com.springboot.framework.study.designpattern.iterator;

import lombok.Data;

/**
 * 系
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 18:38
 */
@Data
public class Department {
    private String name;
    private String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
