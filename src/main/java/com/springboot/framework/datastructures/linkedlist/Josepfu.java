package com.springboot.framework.datastructures.linkedlist;

import lombok.Data;

/**
 * 约瑟夫问题
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/24 12:32
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //add
        circleSingleLinkedList.addBoy(5);
        //list
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确(2->4->1->5->3)
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first节点，当前没有编号
     */
    private Boy first = null;

    /**
     * 添加小孩节点，构建成一个环形的链表
     */
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //创建辅助指针，帮助构建环形链表（有点难理解，建议画图理解）
        Boy curBoy = null;
        //使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(boy);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空~~");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                System.out.println("已经完成~~");
                return;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩除权的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (nums < 1 || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需求创建一个辅助指针（变量）helper，事先应该指向环形链表的最后这个节点
        while (true) {
            //说明helper指向最后小孩节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first 和 helper 移动 k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动 m -1 次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            //说明圈中只有一个节点
            if (helper == first) {
                break;
            }
            //让 first 和helper 指针同时 的移动 countNum -1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}

/**
 * 创建一个Boy类，表示一个节点
 */
@Data
class Boy {
    /**
     * 编号
     */
    private int no;
    /**
     * 指向下一个节点
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }
}