package com.springboot.framework.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 23:55
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = {12, 3, 1, 7, 5, 7, 9, 8, 6};
        quickSort(arr, 0, 8);

//        // 创建要给150000个的随机的数组
//        int[] arr = new int[150000];
//        for (int i = 0; i < 150000; i++) {
//            // 生成一个[0,10000000) 数
//            arr[i] = (int) (Math.random() * 10000000);
//        }
//
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = simpleDateFormat.format(date);
//        System.out.println("排序前的时间是=" + dateStr);
//
//        // 测试排序
//        quickSort(arr, 0, arr.length - 1);
////        System.out.println(Arrays.toString(arr));
//
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);
    }

    /**
     * 快速排序
     * 排序150000条的速度不到1秒，速度比希尔排序快
     */
    static void quickSort(int[] arr, int l, int r) {
        int left = l;
        int right = r;
        // pivot 中轴值
        int pivot = arr[(l + r) / 2];
        // 临时变量，作为交换时使用
        int temp;
        // while循环的目的是让比pivot 值小放到左边，比pivot 值大放到右边
        while (left < right) {
            // 在pivot的左边一直找，直到找到大于等于pivot值，才退出
            while (arr[left] < pivot) {
                left++;
            }
            // 在pivot的右边一直找，直到找到小于等于pivot值，才退出
            while (arr[right] > pivot) {
                right--;
            }
            // 退出
            if (left >= right) {
                break;
            }
            // 交换
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // 防止死循环（例如当left和right同时指向pivot时，比较难理解，建议画图理解）
            if (arr[left] == pivot) {
                right--;
            }
            if (arr[right] == pivot) {
                left++;
            }

        }
//        System.out.println(Arrays.toString(arr));
        // 如果left == right，必须left++，right--，否则出现栈溢出
        if (left == right) {
            left++;
            right--;
        }

        //Todo 有点难理解，建议debug理解
        if (l < right) {
//            System.out.println("向左递归");
//            System.out.println(l);
            quickSort(arr, l, right);
        }
        if (r > left) {
//            System.out.println("向右递归");
//            System.out.println(r);
            quickSort(arr, left, r);
        }
    }
}
