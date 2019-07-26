package com.springboot.framework.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 16:20
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//
//        System.out.printf("排序前\n%s\n", Arrays.toString(arr));

        // 测试一下冒泡排序的速度O(n^2)，给80000个数据，测试
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

//        // 测试冒泡排序
//        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

//        System.out.printf("排序后\n%s\n", Arrays.toString(arr));

    }

    /**
     * 冒泡排序及优化，时间复杂度为O(n^2)
     * 插入80000条的速度大概是9秒
     */
    static void bubbleSort(int[] arr) {
        // 临时变量
        int temp = 0;
        // 标识变量，表示是否进行交换
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            // 重置flag，进行下一次交换
            flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
//            System.out.printf("第%d趟排序后的数组\n%s\n", i + 1, Arrays.toString(arr));
//            System.out.println(Arrays.toString(arr));
        }
    }
}
