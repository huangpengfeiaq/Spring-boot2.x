package com.springboot.framework.study.designpattern.bridge;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/5 1:22
 */
public class Client {
    public static void main(String[] args) {
        // 获取折叠式手机（样式+品牌）
        Phone xiaomi = new FoldedPhone(new XiaoMi());

        xiaomi.open();
        xiaomi.call();
        xiaomi.close();

        System.out.println("===================");
        Phone vivo = new FoldedPhone(new Vivo());

        vivo.open();
        vivo.call();
        vivo.close();

        System.out.println("===================");
        Phone xiaomi2 = new UpRightPhone(new XiaoMi());

        xiaomi2.open();
        xiaomi2.call();
        xiaomi2.close();
    }
}
