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
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {34, 9, -1, 10, 2};
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0,10000000) 数
            arr[i] = (int) (Math.random() * 10000000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是=" + dateStr);

        // 测试选择排序
        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    /**
     * 插入排序
     * 排序80000条的速度大概是1秒
     */
    static void insertSort(int[] arr) {
        for (int j = 1; j < arr.length; j++) {
            // 定义待插入的数
            int insertVal = arr[j];
            int i = j - 1;
            for (; i >= 0; i--) {
                if (arr[i] > insertVal) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = insertVal;
        }
    }
}
