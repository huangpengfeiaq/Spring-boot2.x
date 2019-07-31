package com.springboot.framework.algorithm;

import java.util.Arrays;

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
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    /**
     * 写出我们的kmp搜索算法
     *
     * @param str1 源字符串
     * @param str2 字串
     * @param next 部分匹配表，是字串对应的部分匹配表
     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理 str1.charAt(i) != str2.charAt(j)，去调整j的大小
            //todo KMP算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            // 如果匹配到了
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            // 如果找到了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取到一个字符串（字串）的部分匹配值表
     *
     * @param dest 需要生成的String 字符串
     * @return 部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        // 创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        // 如果字符串的长度为1 部分匹配值就是0
        next[0] = 0;
        // i 表示后缀索引字符，j表示前缀索引字符
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j) 时，我们需要从next[j-1]获取新的j
            // 直到我们发现 有 dest.chatAt(i) == dest.charAt9J)成立才退出
            //todo 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
