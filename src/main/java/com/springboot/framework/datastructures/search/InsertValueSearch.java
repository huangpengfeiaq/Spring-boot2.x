package com.springboot.framework.datastructures.search;

/**
 * 插值查找算法
 * 要求：数组必须是有序的
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 21:23
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 89));
//        System.out.println(binarySearch2(arr, 0, arr.length - 1, 1000));
    }

    /**
     * 插值查找算法
     *
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到就返回下标，否则就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        // 当left>right 时，说明递归整个数组，但是没有找到
        // 注意：findValue < arr[0] || findValue > arr[arr.length - 1] 必须需要，否则我们得到的mid可能越界
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue < midValue) {
            // 向左递归
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            // 向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }
}
