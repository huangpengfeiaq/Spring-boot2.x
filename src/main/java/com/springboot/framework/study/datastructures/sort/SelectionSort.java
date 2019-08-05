package com.springboot.framework.study.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 14:29
 */
public class SelectionSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//
//        System.out.printf("排序前\n%s\n", Arrays.toString(arr));

        // 测试一下选择排序的速度O(n^2)，给80000个数据，测试
        // 创建要给150000个的随机的数组
        int[] arr = new int[150000];
        for (int i = 0; i < 150000; i++) {
            // 生成一个[0,10000000) 数
            arr[i] = (int) (Math.random() * 10000000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是=" + dateStr);

        // 测试选择排序
        selectionSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

//        System.out.printf("排序后\n%s\n", Arrays.toString(arr));

    }

    /**
     * 选择排序及优化，时间复杂度为O(n^2)，
     * 排序150000条的速度大概是5-6秒，速度比冒泡快
     */
    static void selectionSort(int[] arr) {
        // 临时变量
        int min;
        int minIndex;
        // 标识变量，表示是否进行交换
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            minIndex = i;
            // 重置flag，进行下一次交换
            flag = false;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                    flag = true;
                }
            }
            if (flag) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
