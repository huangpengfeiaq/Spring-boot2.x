package com.springboot.framework.datastructures.linkedlist;

import java.util.Stack;

/**
 * 演示Stack的基本使用
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/24 1:48
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        System.out.println(stack.push("jack"));
        System.out.println(stack.push("tom"));
        System.out.println(stack.push("smith"));

        //出栈
        while (stack.size() > 0) {
            //pop就是将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }
}
