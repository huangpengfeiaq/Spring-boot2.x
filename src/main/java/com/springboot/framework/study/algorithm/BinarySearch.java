package com.springboot.framework.study.algorithm;

/**
 * 二分查找算法
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/31 10:39
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6, 7};
        System.out.println(binarySearch(arr, 8));
    }

    /**
     * 二分查找算法
     *
     * @param arr    待查找的数组
     * @param target 需要查找的数
     * @return 返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else if (target > arr[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
