package com.springboot.framework.study.datastructures.search;

/**
 * 线性查找
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 16:32
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = sequenceSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到~~");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     *
     * @param arr   原始数组
     * @param value 要查找的值
     * @return 找到的数组下标
     */
    public static int sequenceSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
