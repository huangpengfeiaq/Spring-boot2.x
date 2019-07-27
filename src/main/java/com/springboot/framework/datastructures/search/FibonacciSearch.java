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
        System.out.println(fibonacciSearch(arr, 1234));

    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     *
     * @param arr       原始数组
     * @param findValue 要查找的值
     * @return 找到的数组下标
     */
    public static int fibonacciSearch(int[] arr, int findValue) {
        int low = 0;
        int high = arr.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        // 存放mid值
        int mid;
        // 获取到斐波那契数列
        int[] f = fib();
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k] 值 可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]，不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需求使用a数组最后的数填充temp
        int lastValue = arr[high];
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = lastValue;
        }

        // 使用while循环处理，找到我们的findValue
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            int midValue = temp[mid];

            if (findValue < midValue) {
                // 向左查找
                high = mid - 1;
                k--;
            } else if (findValue > midValue) {
                // 向右查找
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
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
