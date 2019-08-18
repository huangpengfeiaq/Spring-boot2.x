package com.springboot.framework.service;

import com.springboot.framework.constant.BaseServiceMethodsEnum;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.vo.ResponseVO;

/**
 * 通用Service接口
 *
 * @param <T> 对应的POJO类
 * @author huangpengfei
 * @version 1.1.190818
 * @date 2019/8/15 20:55
 */
public interface BaseService<T> {
    /**
     * 删除一个对象（根据主键）
     *
     * @param entity 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> deleteByPrimaryKey(T entity);

    /**
     * 新增一个对象
     *
     * @param entity 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> insertSelective(T entity);

    /**
     * 查询一个对象（根据主键）
     *
     * @param primaryKey 主键
     * @return ResponseVO<Order>
     */
    ResponseVO<T> selectByPrimaryKey(Integer primaryKey);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseVO
     */
    PageResponseVO selectList(Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @param entity 数据传输对象
     * @return ResponseVO<Integer>
     */
    ResponseVO<Integer> selectCount(T entity);

    /**
     * 更新一个对象（根据主键）
     *
     * @param entity 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> updateByPrimaryKeySelective(T entity);

    /**
     * 参数校验
     *
     * @param entity 数据传输对象
     * @param type   校验类型
     * @return 成功则返回Errors.SUCCESS，否则返回错误信息
     */
    Errors validRequest(T entity, BaseServiceMethodsEnum type);
}
