package com.springboot.framework.study.datastructures.stack;

import lombok.Data;

import java.util.Scanner;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/24 18:02
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下
        //先创建一个ArrayStack对象
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        //控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("push：添加数据到栈");
            System.out.println("pop：从栈取出数据");
            System.out.println("exit：退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.printf("出栈的数据是 %d\n", arrayStack.pop());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出~~");
    }
}

/**
 * 定义一个类，表示栈
 */
@Data
class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组，数组模拟栈，数据就放在该数组中
     */
    private int[] stack;
    /**
     * 栈顶，初始化为-1
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空~~");
        }
        return stack[top--];
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈已空~~");
            return;
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}