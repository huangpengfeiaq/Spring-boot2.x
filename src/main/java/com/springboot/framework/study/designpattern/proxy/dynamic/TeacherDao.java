package com.springboot.framework.study.designpattern.proxy.dynamic;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 0:55
 */
public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println(" 老师正在授课中。。。");
    }
}
