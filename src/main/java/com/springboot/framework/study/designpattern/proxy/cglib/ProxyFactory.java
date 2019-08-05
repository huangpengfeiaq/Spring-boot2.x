package com.springboot.framework.study.designpattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 1:25
 */
public class ProxyFactory implements MethodInterceptor {
    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回一个代理对象，是target 对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxyInstance() {
        // 1.创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数
        enhancer.setCallback(this);
        // 4.创建子类对象，即代理对象
        return enhancer.create();
    }

    /**
     * 重写 intercept 方法，会调用目标对象的方法
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理模式~~ 开始");
        Object returnVal = method.invoke(target, objects);
        System.out.println("Cglib代理模式~~ 提交");
        return returnVal;
    }
}
