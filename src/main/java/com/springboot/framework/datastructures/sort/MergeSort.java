package com.springboot.framework.datastructures.sort;

/**
 * 归并排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 10:30
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr);
    }

    /**
     * 合并
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    static void merge(int[] arr, int left, int mid, int right, int[] temp) {
    }

    /**
     * 归并排序
     */
    static void mergeSort(int[] arr) {
    }
}
