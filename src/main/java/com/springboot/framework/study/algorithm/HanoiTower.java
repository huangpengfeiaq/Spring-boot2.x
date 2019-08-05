package com.springboot.framework.study.algorithm;

/**
 * 分治算法
 * 解决汉诺塔问题
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/31 11:17
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果我们有 n >= 2 情况，我们总是可以看作是两个盘（1.最底下的一个盘 2.上面的所有盘）
            // 1.先把 最上面的所有盘 A -> B，移动过程会使用到 c塔
            hanoiTower(num - 1, a, c, b);
            // 2.再把 最底下的一个盘 A -> C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3.最后 把上面的所有盘 B -> C，移动过程会使用到 a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
