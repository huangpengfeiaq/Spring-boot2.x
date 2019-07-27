package com.springboot.framework.datastructures.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找法
 * 要求：数组必须是有序的
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 16:54
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1000));
        System.out.println(binarySearch2(arr, 0, arr.length - 1, 1000));
    }

    /**
     * 二分查找算法
     *
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到就返回下标，否则就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        // 当left>right 时，说明递归整个数组，但是没有找到
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue < midValue) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

    static List<Integer> resIndexList = new ArrayList<>();

    /**
     * 二分查找算法（返回所有索引值）
     *
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到就返回下标，否则就返回-1
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
        // 当left>right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue < midValue) {
            // 向左递归
            return binarySearch2(arr, left, mid - 1, findValue);
        } else if (findValue > midValue) {
            // 向右递归
            return binarySearch2(arr, mid + 1, right, findValue);
        } else {
            resIndexList.add(mid);
            for (int i = mid; i > 0; ) {
                if (arr[--i] == findValue) {
                    resIndexList.add(i);
                }
            }
            for (int j = mid; j < arr.length - 1; ) {
                if (arr[++j] == findValue) {
                    resIndexList.add(j);
                }
            }
            return resIndexList;
        }
    }
}
