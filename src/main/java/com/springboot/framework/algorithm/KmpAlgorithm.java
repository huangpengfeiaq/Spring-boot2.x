package com.springboot.framework.algorithm;

/**
 * KMP算法
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/31 21:25
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("AA");
    }

    /**
     * 获取到一个字符串（字串）的部分匹配值表
     *
     * @param dest
     * @return 部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        // 创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        // 如果字符串的长度为1 部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
