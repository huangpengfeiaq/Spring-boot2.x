package com.springboot.framework.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 21:27
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
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
        shellSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    /**
     * 希尔排序，
     * 排序150000条的速度大概是0-1秒，速度比插入快
     */
    static void shellSort(int[] arr) {

        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                // 定义待插入的数
                int insertVal = arr[i];
                // 获取前一个数的索引
                int j = i - gap;
                // 从当前i位置往前遍历
                for (; j >= 0; j -= gap) {
                    if (arr[j] > insertVal) {
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = insertVal;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
