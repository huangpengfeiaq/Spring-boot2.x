package com.springboot.framework.datastructures.recursion;

/**
 * 迷宫回溯问题
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/25 23:45
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图（8行7列）
        int rows = 8;
        int columns = 7;
        int[][] map = new int[rows][columns];

        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < columns; i++) {
            map[0][i] = 1;
            map[rows - 1][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < rows; i++) {
            map[i][0] = 1;
            map[i][columns - 1] = 1;
        }
        // 设置挡板，1 表示
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        System.out.println("地图的情况~~");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(" " + map[i][j]);
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);
        // 输出新的地图，小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的 地图的情况~~");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(" " + map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1. map 表示地图
     * 2. i，j 表示从地图的哪个位置开始出发（1，1）
     * 3. 如果小球能到 map[rows-2][columns-2]，说明通路找到
     * 4. 约定：当map[i][j] 为0时表示该点没有走过 当为1表示墙 ；2 表示通路可以走 ；3 表示该点已经走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略（方法） 下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
//        //我的思路
//        map[i][j] = 2;
//        if (map[1][3] == 2) {
//            return true;
//        } else if (map[i + 1][j] == 0) {
//            i++;
//        } else if (map[i][j + 1] == 0) {
//            j++;
//        } else if (map[i - 1][j] == 0) {
//            i--;
//        } else if (map[i][j - 1] == 0) {
//            j--;
//        } else {
//            map[i][j] = 3;
//            System.out.printf("map[%d][%d]为死路\n", i, j);
//            return false;
//        }
//        setWay(map, i, j);
//        return false;

        //老师的思路
        if (map[1][3] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    System.out.printf("map[%d][%d]为死路\n", i, j);
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
