package com.springboot.framework.datastructures.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法（mid=low+F(k-1)-1）
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 22:00
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(Arrays.toString(fib()));

    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     *
     * @param arr 原始数组
     * @param key 要查找的值
     * @return 找到的数组下标
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        // 存放mis值
        int mid = 0;
        // 获取到斐波那契数列
        int[] f = fib();
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k] 值 可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]，不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需求使用a数组最后的数填充temp
        int lastValue = arr[high];
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = lastValue;
        }

        // 使用while循环处理，找到我们的key
        mid = low + f[k - 1] - 1;
        if (low < high) {
            // 向左递归
            return fibonacciSearch(arr, left, mid - 1, findValue);
        } else if (low > high) {
            // 向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

    private static int maxSize = 20;

    /**
     * 因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
     *
     * @return 斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

//    static int[] f = new int[maxSize];
//    private static void setFib(int i) {
//        if (i > maxSize - 1) {
//            return;
//        }
//        f[i] = f[i - 1] + f[i - 2];
//        setFib(++i);
//    }
}
