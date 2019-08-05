package com.springboot.framework.study.datastructures.recursion;

/**
 * 8皇后问题
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/26 10:27
 */
public class Queen8 {
    /**
     * 定义一个max表示共有多少个皇后
     */
    int max = 8;
    /**
     * 定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
     */
    int[] array = new int[max];
    /**
     * 统计一共的解法
     */
    int count = 0;
    /**
     * 统计一共的解法
     */
    int judgeCount = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n", queen8.count);
        System.out.printf("一共判断冲突的次数为%d次\n", queen8.judgeCount);
    }


    /**
     * 编写一个方法，放置第n个皇后以后的所有皇后
     *
     * @param index 表示第n个皇后的索引值
     */
    private void check(int index) {
        // index == 8，表示第8个皇后已经放好
        if (index == max) {
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        // 落子
        for (int i = 0; i < max; i++) {
            // 放入棋子
            array[index] = i;
            // 判断是否冲突，若不冲突，继续放入下一颗棋子。反之，继续以下一列的棋子判断
            if (judge(index)) {
                check(index + 1);
            }
            // 直到i==max-1，会被抛弃
        }
    }

    /**
     * 查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param index 表示第n个皇后的索引值
     * @return 是否可放置
     */
    private boolean judge(int index) {
        judgeCount++;
        // 遍历n之前的位置，判断是否在同一列或同一斜线
        for (int i = 0; i < index; i++) {
            if (array[index] == array[i] || Math.abs(index - i) == Math.abs(array[index] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写一个方法，可以将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
//            System.out.printf("array[%d]=%d ", i, array[i]);
            System.out.printf("第%d行第%d个。", i + 1, array[i] + 1);
        }
        System.out.println();
    }
}
