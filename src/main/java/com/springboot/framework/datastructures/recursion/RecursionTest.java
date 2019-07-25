package com.springboot.framework.datastructures.recursion;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/25 23:01
 */
public class RecursionTest {
    public static void main(String[] args) {
        // 通过打印问题，回顾递归调用机制
        test(4);

        int res = factorial(3);
        System.out.println("res=" + res);
    }

    /**
     * 打印问题
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.printf("n=%d\n", n);
    }

    /**
     * 阶层问题
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
