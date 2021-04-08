package com.springboot.framework.service;

import com.springboot.framework.constant.Errors;

import java.util.List;

/**
 * 通用Service接口
 *
 * @param <T> 对应的POJO类
 * @author huangpengfei
 * @version 1.1.190818
 */
public interface BaseService<T> {
    /**
     * 删除一个对象（根据主键）
     *
     * @param entity 数据传输对象
     * @return 成功：1，失败：0
     */
    Errors deleteByPrimaryKey(T entity);

    /**
     * 新增一个对象
     *
     * @param entity 数据传输对象
     * @return 成功：1，失败：0
     */
    Errors insertSelective(T entity);

    /**
     * 查询一个对象（根据主键）
     *
     * @param primaryKey 主键
     * @return 对象实体
     */
    T selectByPrimaryKey(Integer primaryKey);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 此对象所有数据列表
     */
    List<T> selectList(Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @param entity 数据传输对象
     * @return 查询到的数量
     */
    Integer selectCount(T entity);

    /**
     * 更新一个对象（根据主键）
     *
     * @param entity 数据传输对象
     * @return 成功：1，失败：0
     */
    Errors updateByPrimaryKeySelective(T entity);
}
