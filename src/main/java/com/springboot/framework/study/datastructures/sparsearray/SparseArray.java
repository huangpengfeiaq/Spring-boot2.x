package com.springboot.framework.study.datastructures.sparsearray;

/**
 * 稀疏数组
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/22 16:36
 */
public class SparseArray {
    public static void main(String[] args) {
        //TODO 创建一个原始的二维数组11*11
        //0没有棋子，1黑子，2白字
        int[][] chessArr = new int[6][11];
        //黑子
        chessArr[1][2] = 1;
        //白子
        chessArr[2][3] = 2;
        chessArr[5][2] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        //TODO 将二维数组 转 稀疏数组
        //1.先遍历得到非0数据的个数
        int rows = 6;
        int cols = 11;
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = rows;
        sparseArray[0][1] = cols;
        sparseArray[0][2] = sum;

        //遍历二维数组。将非0的值存放到sparseArray中
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为~~~");
        for (int[] row : sparseArray) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        //TODO 将稀疏数组恢复成二维数组
        //1.先读取稀疏数组第一行，根据第一行的数据，创建原始的二维数组
        int[][] newArray = new int[sparseArray[0][0]][sparseArray[0][1]];

        //2.赋值（从第二行开始）
        for (int i = 1; i < sparseArray.length; i++) {
            newArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //输出二维数组
        System.out.println();
        System.out.println("恢复后的二维数组为~~~");
        for (int[] row : newArray) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }
}
