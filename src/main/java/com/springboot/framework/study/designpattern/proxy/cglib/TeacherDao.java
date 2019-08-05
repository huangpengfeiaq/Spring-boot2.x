package com.springboot.framework.study.designpattern.proxy.cglib;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 0:55
 */
public class TeacherDao {
    public void teach() {
        System.out.println(" 老师授课中，我是cglib代理，不需要实现接口。。。");
    }
}
