package com.springboot.framework.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 16:23
 */
public class InsertionSort {
    public static void main(String[] args) {
//        int[] arr = {34, 9, -1, 10, 2};
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
        insertionSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    /**
     * 插入排序，时间复杂度为O(n^2)
     * 排序150000条的速度大概是1-2秒，速度比选择快
     */
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            int insertVal = arr[i];
            // 获取前一个数的索引
            int j = i - 1;
            // 从当前i位置往前遍历
            for (; j >= 0; j--) {
                if (arr[j] > insertVal) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = insertVal;
        }
    }
}
