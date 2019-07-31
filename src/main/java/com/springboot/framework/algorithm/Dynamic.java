package com.springboot.framework.algorithm;

/**
 * 动态规划算法(背包问题)
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/31 17:15
 */
public class Dynamic {
    public static void main(String[] args) {
        // 物品的重量（因为表格中第一行与第一列均留空，所以第一个数据用0填充）
        int[] weight = {0, 1, 4, 3};
        // 物品的价值（因为表格中第一行与第一列均留空，所以第一个数据用0填充）
        int[] value = {0, 1500, 3000, 2000};
        String[] name = {"", "吉他", "音响", "电脑"};
        // 背包的容量
        int capacity = 4;
        // 物品的个数
        int itemsNum = value.length - 1;

        // 创建二维数组，表示表格
        // v[i][j] 表示在前i个物品中能加入的背包的总容量为j的物品
        int[][] v = new int[itemsNum + 1][capacity + 1];
        // 为了记录放入商品的情况，我们定一个二维数组
        String[][] path = new String[itemsNum + 1][capacity + 1];

        // 初始化第一行和第一列，在本程序中，可以不去处理，因为默认就是0
        // 将第一列设置为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
            path[i][0] = "";
        }
        // 将第一行设置为0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
            path[0][i] = "";
        }
        // 根据前面得到的公司来动态规划处理
        // 不处理第一行
        for (int i = 1; i < v.length; i++) {
            // 不处理第一列
            for (int j = 1; j < v[i].length; j++) {
                // 公式
                if (weight[i] > j) {
                    v[i][j] = v[i - 1][j];
                    path[i][j] = path[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], value[i] + v[i - 1][j - weight[i]]);
                    // 为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现
                    if (v[i - 1][j] >= value[i] + v[i - 1][j - weight[i]]) {
                        v[i][j] = v[i - 1][j];
                        path[i][j] = path[i - 1][j];
                    } else {
                        v[i][j] = value[i] + v[i - 1][j - weight[i]];
                        path[i][j] = name[i] + path[i - 1][j - weight[i]];
                    }
                }
            }
        }

        // 输出一下v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~");
        // 输出一下path
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~");
        System.out.println("结果为：" + path[itemsNum][capacity]);
    }
}
