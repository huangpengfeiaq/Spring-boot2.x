package com.springboot.framework.constant;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/18 10:56
 */
public enum BaseServiceMethodsEnum {
    // 1.提供当前枚举类的多个对象：用,隔开 ;结束
    //
    DELETE_BY_PRIMARY_KEY(1, "删除一个对象（根据主键）"),
    //
    INSERT_SELECTIVE(2, "新增一个对象"),
    //
    SELECT_BY_PRIMARY_KEY(3, "查询一个对象（根据主键）"),
    //
    SELECT_LIST(4, "列表查询"),
    //
    SELECT_COUNT(5, "总数查询"),
    //
    UPDATE_BY_PRIMARY_KEY_SELECTIVE(6, "更新一个对象（根据主键）"),
    //
    CUSTOM(0, "自定义");

    // 2.声明Season对象的属性
    private final int identifier;
    private final String desc;

    // 3.私有化类的构造器，并给对象属性赋值
    BaseServiceMethodsEnum(int identifier, String desc) {
        this.identifier = identifier;
        this.desc = desc;
    }
}
