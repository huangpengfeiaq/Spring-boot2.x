package com.springboot.framework.datastructures.sort;

import java.util.Arrays;

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
        int[] temp = new int[arr.length];
        mergeSor(arr, 0, 7, temp);
    }

    /**
     * 归并排序
     */
    static void mergeSor(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSor(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSor(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);
        }
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
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 初始化i，左边有序序列的初始索引
        int i = left;
        // 初始化j，右边有序序列的初始索引
        int j = mid + 1;
        // 初始化t，temp数组的当前索引
        int t = 0;

        // 先把左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
//        while (i <= mid && j <= right) {
//            if (arr[i] <= arr[j]) {
//                temp[t++] = arr[i++];
//            } else if (arr[i] > arr[j]) {
//                temp[t++] = arr[j++];
//            }
//        }
        for (; i <= mid && j <= right; ) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else if (arr[i] > arr[j]) {
                temp[t++] = arr[j++];
            }
        }

        // 把有剩余数据的一边的数据依次全部填充到temp
        if (i == mid + 1) {
            for (; j <= right; ) {
                temp[t++] = arr[j++];
            }
        } else if (j == right + 1) {
            for (; i <= mid; ) {
                temp[t++] = arr[i++];
            }
        }

        //Todo 不太理解！！
        // 将temp数组的元素全部拷贝到arr
        // 注意，不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

        System.out.println(Arrays.toString(temp));
        System.out.println(Arrays.toString(arr));
    }
}
