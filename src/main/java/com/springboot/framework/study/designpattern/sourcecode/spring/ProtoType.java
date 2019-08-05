package com.springboot.framework.study.designpattern.sourcecode.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 10:42
 */
public class ProtoType {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 获取monster[通过id获取monster]
        Object bean = applicationContext.getBean("id01");
        System.out.println("bean" + bean);
    }
}
