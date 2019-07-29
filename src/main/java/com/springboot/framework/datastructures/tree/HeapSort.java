package com.springboot.framework.datastructures.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/29 10:52
 */
public class HeapSort {
    public static void main(String[] args) {
        // 要求将数组进行升序排序
//        int[] arr = {4, 6, 8, 5, 9};

//        // 分布完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("first:"+ Arrays.toString(arr));
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("second:"+ Arrays.toString(arr));


        // 测试一下冒泡排序的速度O(n^2)，给80000个数据，测试
        // 创建要给10000000个的随机的数组
        int[] arr = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            // 生成一个[0,10000000) 数
            arr[i] = (int) (Math.random() * 10000000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是=" + dateStr);

        // 最终完整版代码
        // 构成大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
//        System.out.println("大顶堆=" + Arrays.toString(arr));
        int temp = 0;
        // 将大顶堆转化成数组
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
//        System.out.println("数组=" + Arrays.toString(arr));


        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    /**
     * 编写一个堆排序的方法，时间复杂度为O(n*log2n)
     * 排序10000000条的速度大概是2-3秒，速度超级快
     */
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
    }

    /**
     * 将一个数组（完全二叉树），调整成一个大顶堆
     * 功能：完成将以 i 对应的非叶子结点的树调整成大顶堆
     * 举例 int[] arr = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 arr = {4, 9, 8, 5, 6};
     * 如果我们再次调用 adjustHeap 传入的是i = 0 => 得到 arr = {9, 6, 8, 5, 4};
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中的索引
     * @param length 表示多少个元素调整，length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        //Todo 开始调整（以下代码特别难理解，建议画图理解，或者debug理解）
        // 说明：1. k = left(i) 是 i结点的左子结点（切记：i由大到小遍历，原因是遍历方向为从左到右，从下到上）
        for (int k = left(i); k < length; k = left(k)) {
            // 如果左子结点值小于右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k 指向右子结点
                k++;
            }
            // 如果子结点大于父结点
            if (arr[k] > temp) {
                // 把较大的值赋给当前结点
                arr[i] = arr[k];
                // !!! i 指向 k，继续循环比较
                i = k;
            } else {
                break;
            }
        }
        // 当for 循环结束后，我们已经将以 i 为父结点的树的最大值，放在了 最顶端（局部）
        // 将temp值放到调整后的位置
        arr[i] = temp;
    }

    /**
     * 左子结点 => this.left
     */
    private static int left(int n) {
        return 2 * n + 1;
    }

    /**
     * 右子结点 => this.right
     */
    private static int right(int n) {
        return 2 * n + 2;
    }
}
