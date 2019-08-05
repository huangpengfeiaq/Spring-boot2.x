package com.springboot.framework.study.designpattern.proxy.cglib;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 1:33
 */
public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        TeacherDao target = new TeacherDao();
        // 获取到代理对象，并且将目标对象传递给代理对象
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(target).getProxyInstance();

        // 执行代理对象的方法，触发intercept 方法，从而实现 对目标对象的调用
        proxyInstance.teach();
    }
}
