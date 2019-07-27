package com.springboot.framework.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/27 14:14
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        // 测试一下冒泡排序的速度O(n^2)，给80000个数据，测试
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

        // 测试排序
        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

//        System.out.printf("排序后\n%s\n", Arrays.toString(arr));
    }

    /**
     * 基数排序
     * 排序150000条的速度不到1秒，速度比归并排序快
     */
    static void radixSort(int[] arr) {
        // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 说明
        // 1.二维数组包含10个一维数组
        // 2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length
        // 3.很明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        // 可以这样理解，bucketElementCounts[0]，记录的就是bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        // 得到数组中的最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        for (int i = 0, step = 1; i < maxLength; i++, step = step * 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的个位的值
                int digitOfElement = arr[j] / step % 10;

                // 放入到对应的桶中，并更新bucketElementCounts[digitOfElement]的值。
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = arr[j];
            }
            // 按bucket顺序取出值到arr
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
                // 每个桶数据取出后，需要将每个 bucketElementCounts[k] = 0 !!!
                bucketElementCounts[j] = 0;
            }
        }
    }
}
