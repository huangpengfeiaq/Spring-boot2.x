package com.springboot.framework.datastructures.queue;

import java.util.Scanner;

/**
 * 数组实现环形队列
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/22 20:03
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组模拟环形队列的案例~~");
        //创建一个环形队列
        //说明这里设置的4，其队列有效数据是3
        CircleArrayQueue queue = new CircleArrayQueue(4);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        //输出一个菜单
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", result);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

/**
 * 使用数组模拟队列-编写一个CircleArrayQueue类
 */
class CircleArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头
     * front 变量含义做一个调整：front就指向队列的第一个元素，也就是arr[front]
     * front 的初始值=0
     */
    private int front;
    /**
     * 队列尾
     * rear 变量含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
     * rear 的初始值=0
     */
    private int rear;
    /**
     * 该数组用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     */
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据，出队列
     */
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取出数据");
        }
//        front++; //让front 后移
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        int value = arr[front];
        //2.将front后移，考虑取模（防止数组越界）
        front = (front + 1) % maxSize;
        //3.将临时保存的变量返回
        return value;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        //判断队列是否空
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        //动脑筋
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     */
    public int size() {
        // 不理解可以代数实测
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据，注意不是取出数据
     */
    public int headQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空的，没有数据~");
        }
        return arr[front];
    }
}