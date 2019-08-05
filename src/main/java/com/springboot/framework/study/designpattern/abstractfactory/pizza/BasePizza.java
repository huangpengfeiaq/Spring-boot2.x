package com.springboot.framework.study.designpattern.abstractfactory.pizza;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/3 15:22
 */
@Data
public abstract class BasePizza {
    protected String name;

    /**
     * 准备原材料，不同的披萨不一样，因此，我们做成抽象方法
     */
    public abstract void prepare();

    /**
     * 烘烤
     */
    public void bake() {
        System.out.println(name + " baking");
    }

    /**
     * 切割
     */
    public void cut() {
        System.out.println(name + " cutting");
    }

    /**
     * 打包
     */
    public void box() {
        System.out.println(name + " boxing");
    }
}
