package com.springboot.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.springboot.framework.constant.BaseServiceMethodsEnum;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.exception.BusinessException;
import com.springboot.framework.service.BaseService;

import java.util.List;

import static com.springboot.framework.constant.BaseServiceMethodsEnum.INSERT_SELECTIVE;
import static com.springboot.framework.constant.BaseServiceMethodsEnum.UPDATE_BY_PRIMARY_KEY_SELECTIVE;
import static com.springboot.framework.constant.Errors.*;

/**
 * 通用业务处理实现类
 * 【已遗弃】由于实际开发以及类自身缺陷，暂时不予使用。待后期优化
 *
 * @author huangpengfei
 * @version 2.0.210419
 * @since 2019/8/15 23:23
 */
@Deprecated
public abstract class BaseServiceImpl<T> implements BaseService<T> {
//    @Resource
    private BaseMapper<T> entityMapper;

    @Override
    public Errors deleteByPrimaryKey(T entity) {
        // 2.响应校验
        if (entityMapper.updateById(entity) != 1) {
            throw new BusinessException(SYSTEM_DELETE_FAIL);
        }
        return SUCCESS;
    }

    @Override
    public Errors insertSelective(T entity) {
        //1.请求校验
        Errors errors = validRequest(entity, INSERT_SELECTIVE);
        if (errors != SUCCESS) {
            throw new BusinessException(errors);
        }
        // 2.响应校验
        if (entityMapper.insert(entity) != 1) {
            throw new BusinessException(SYSTEM_INSERT_FAIL);
        }
        return SUCCESS;
    }

    @Override
    public T selectByPrimaryKey(Integer primaryKey) {
        //1.请求校验
        T entity = entityMapper.selectById(primaryKey);
        // 2.响应校验
        if (entity == null) {
            throw new BusinessException(SYSTEM_DATA_NOT_FOUND);
        }
        return entity;
    }

    /**
     * 用于列表显示的Example
     *
     * @return 用于列表显示的Example
     */
    protected abstract QueryWrapper getExampleForSelectList();

    @Override
    public List<T> selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return entityMapper.selectList(getExampleForSelectList());
    }

    @Override
    public Long selectCount(T entity) {
        return entityMapper.selectCount(new QueryWrapper<>(entity));
    }

    @Override
    public Errors updateByPrimaryKeySelective(T entity) {
        // 1.请求校验
        Errors errors = validRequest(entity, UPDATE_BY_PRIMARY_KEY_SELECTIVE);
        if (errors != SUCCESS) {
            throw new BusinessException(errors);
        }
        // 2.响应校验
        if (entityMapper.updateById(entity) != 1) {
            throw new BusinessException(SYSTEM_UPDATE_ERROR);
        }
        return SUCCESS;
    }

    /**
     * 参数校验
     *
     * @param entity 数据传输对象
     * @param type   校验类型
     * @return 成功则返回Errors.SUCCESS，否则返回错误信息
     */
    protected abstract Errors validRequest(T entity, BaseServiceMethodsEnum type);
}
