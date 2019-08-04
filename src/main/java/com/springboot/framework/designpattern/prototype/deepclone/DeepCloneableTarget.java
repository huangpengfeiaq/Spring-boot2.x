package com.springboot.framework.designpattern.prototype.deepclone;

import java.io.Serializable;

/**
 * 深拷贝
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/4 13:23
 */
public class DeepCloneableTarget implements Cloneable, Serializable {
    private static final long SERIAL_VERSION_UID = 1L;
    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    /**
     * 因为该类的属性，都是String，因此我们这里使用默认的clone完成即可
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
