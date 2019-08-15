package com.springboot.framework.service;

import com.springboot.framework.constant.Errors;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.vo.ResponseVO;

/**
 * 通用Service接口
 *
 * @param <POJO> 对应的POJO类
 * @param <DTO>  对应的DTO类
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/15 20:55
 */
public interface BaseService<POJO, DTO> {
    /**
     * 删除一个对象（根据主键）
     *
     * @param pojoDTO 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> deleteByPrimaryKey(DTO pojoDTO);

    /**
     * 新增一个对象
     *
     * @param pojoDTO 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> insertSelective(DTO pojoDTO);

    /**
     * 查询一个对象（根据主键）
     *
     * @param primaryKey 主键
     * @return ResponseVO<Order>
     */
    ResponseVO<POJO> selectByPrimaryKey(Integer primaryKey);

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
     * @return ResponseVO<Integer>
     */
    ResponseVO<Integer> selectCount();

    /**
     * 更新一个对象（根据主键）
     *
     * @param pojoDTO 数据传输对象
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> updateByPrimaryKeySelective(DTO pojoDTO);
}
