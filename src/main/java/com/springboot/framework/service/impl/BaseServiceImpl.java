package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.constant.BaseServiceMethodsEnum;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.exception.BusinessException;
import com.springboot.framework.service.BaseService;
import com.springboot.framework.utils.PageUtil;
import com.springboot.framework.utils.ResponseVOUtil;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.vo.ResponseVO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.springboot.framework.constant.BaseServiceMethodsEnum.INSERT_SELECTIVE;
import static com.springboot.framework.constant.BaseServiceMethodsEnum.UPDATE_BY_PRIMARY_KEY_SELECTIVE;
import static com.springboot.framework.constant.Errors.SUCCESS;

/**
 * 通用业务处理实现类
 *
 * @author huangpengfei
 * @version 1.1.190818
 * @date 2019/8/15 23:23
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Resource
    private Mapper<T> entityMapper;

    @Override
    public Errors deleteByPrimaryKey(T entity) {
        // 2.响应校验
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            throw new BusinessException(2, "删除失败");
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
        if (entityMapper.insertSelective(entity) != 1) {
            throw new BusinessException(2, "添加失败");
        }
        return SUCCESS;
    }

    @Override
    public T selectByPrimaryKey(Integer primaryKey) {
        return entityMapper.selectByPrimaryKey(primaryKey);
    }

    /**
     * 用于列表显示的Example
     *
     * @return 用于列表显示的Example
     */
    protected abstract Example selectListByExample();

    @Override
    public List<T> selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = selectListByExample();

        return entityMapper.selectByExample(example);
    }

    @Override
    public Integer selectCount(T entity) {
        return entityMapper.selectCount(entity);
    }

    @Override
    public Errors updateByPrimaryKeySelective(T entity) {
        // 1.请求校验
        Errors errors = validRequest(entity, UPDATE_BY_PRIMARY_KEY_SELECTIVE);
        if (errors != SUCCESS) {
            throw new BusinessException(errors);
        }
        // 2.响应校验
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            throw new BusinessException(2, "更新失败");
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
