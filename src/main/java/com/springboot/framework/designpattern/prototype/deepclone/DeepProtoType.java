package com.springboot.framework.designpattern.prototype.deepclone;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.Data;

import java.io.*;

/**
 * 深拷贝
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 13:28
 */
@Data
public class DeepProtoType implements Cloneable, Serializable {
    private String name;
    private DeepCloneableTarget deepCloneableTarget;

    public DeepProtoType() {
    }

    /**
     * 深拷贝 - 方式1 使用clone 方法
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 这里完成对基本数据类似（属性）和String的克隆
        deep = super.clone();
        // 对引用类型的属性，进行单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.setDeepCloneableTarget((DeepCloneableTarget) deepCloneableTarget.clone());

        return deepProtoType;
    }

    /**
     * 深拷贝 - 方式2 通过对象的序列化实现（推荐）
     */
    protected Object deepClone() {
        // 创建流对象
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            // 当前这个对象以对象流的方式输出
            oos.writeObject(this);

            // 反序列化
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            DeepProtoType copyObj = (DeepProtoType) ois.readObject();
            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭流
            try {
                ois.close();
                bais.close();
                oos.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
