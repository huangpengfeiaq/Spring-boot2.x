package com.springboot.framework.study.designpattern.prototype.deepclone;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 13:37
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepProtoType p = new DeepProtoType();
        p.setName("宋江");
        p.setDeepCloneableTarget(new DeepCloneableTarget("大牛", "小牛"));

        // 方式1 完成深拷贝
        DeepProtoType p2 = (DeepProtoType) p.clone();

        System.out.println("p.name=" + p.getName() + ";" + p.getDeepCloneableTarget().hashCode());
        System.out.println("p2.name=" + p2.getName() + ";" + p2.getDeepCloneableTarget().hashCode());

        // 方式2 完成深拷贝
        DeepProtoType p3 = (DeepProtoType) p.deepClone();

        System.out.println("p.name=" + p.getName() + ";" + p.getDeepCloneableTarget().hashCode());
        System.out.println("p2.name=" + p3.getName() + ";" + p3.getDeepCloneableTarget().hashCode());
    }
}
