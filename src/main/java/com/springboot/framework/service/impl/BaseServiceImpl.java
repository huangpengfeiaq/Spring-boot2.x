package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.service.BaseService;
import com.springboot.framework.utils.PageUtil;
import com.springboot.framework.utils.ResponseVOUtil;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.vo.ResponseVO;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通用业务处理实现类
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/15 23:23
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Resource
    private Mapper<T> entityMapper;

    @Override
    public ResponseVO<Errors> deleteByPrimaryKey(T entity) {
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            return ResponseVOUtil.fail("删除失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseVO<Errors> insertSelective(T entity) {
        if (entityMapper.insertSelective(entity) != 1) {
            return ResponseVOUtil.fail("添加失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseVO<T> selectByPrimaryKey(Integer primaryKey) {
        return ResponseVOUtil.success(entityMapper.selectByPrimaryKey(primaryKey));
    }

    @Override
    public PageResponseVO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<T> list = entityMapper.selectAll();
        return PageUtil.page(list);
    }

    @Override
    public ResponseVO<Integer> selectCount(T entity) {
        return ResponseVOUtil.success(entityMapper.selectCount(entity));
    }

    @Override
    public ResponseVO<Errors> updateByPrimaryKeySelective(T entity) {
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            return ResponseVOUtil.fail("更新失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }
}
