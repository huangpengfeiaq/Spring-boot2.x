package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.constant.BaseServiceMethodsEnum;
import com.springboot.framework.exception.BusinessException;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dao.mapper.AdminMapper;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.springboot.framework.constant.BaseServiceMethodsEnum.INSERT_SELECTIVE;
import static com.springboot.framework.constant.BaseServiceMethodsEnum.UPDATE_BY_PRIMARY_KEY_SELECTIVE;
import static com.springboot.framework.constant.Errors.*;

/**
 * 管理员业务处理类
 * > @Transactional事务，@Scheduled定时器
 *
 * @author huangpengfei
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    private AdminMapper entityMapper;

//    /**
//     * 自定义定时任务，每天23点执行一次
//     */
//    @Scheduled(cron = "0 0 23 * * ?")
//    public void customScheduled() {
//        log.info("---------------- 定时任务 ----------------");
//        log.info("customScheduled被执行了...");
//    }

    @Override
    public Errors deleteByPrimaryKey(Admin entity) {
        // 2.响应校验
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            throw new BusinessException(SYSTEM_DELETE_FAIL);
        }
        return SUCCESS;
    }

    @Override
    public Errors insertSelective(Admin entity) {
        //1.请求校验
        Errors errors = validRequest(entity, INSERT_SELECTIVE);
        if (errors != SUCCESS) {
            throw new BusinessException(errors);
        }
        // 2.响应校验
        if (entityMapper.insertSelective(entity) != 1) {
            throw new BusinessException(SYSTEM_INSERT_FAIL);
        }
        return SUCCESS;
    }

    @Override
    public Admin selectByPrimaryKey(Integer primaryKey) {
        //1.请求校验
        Admin entity = entityMapper.selectByPrimaryKey(primaryKey);
        // 2.响应校验
        if (entity == null) {
            throw new BusinessException(SYSTEM_DATA_NOT_FOUND);
        }
        return entity;
    }

    @Override
    public List<Admin> selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        example.orderBy("createDate").desc();

        return entityMapper.selectByExample(example);
    }

    @Override
    public Integer selectCount(Admin entity) {
        return entityMapper.selectCount(entity);
    }

    @Override
    public Errors updateByPrimaryKeySelective(Admin entity) {
        // 1.请求校验
        Errors errors = validRequest(entity, UPDATE_BY_PRIMARY_KEY_SELECTIVE);
        if (errors != SUCCESS) {
            throw new BusinessException(errors);
        }
        // 2.响应校验
        if (entityMapper.updateByPrimaryKeySelective(entity) != 1) {
            throw new BusinessException(SYSTEM_UPDATE_ERROR);
        }
        return SUCCESS;
    }

    @Override
    public Admin login(String loginKey, String password) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, CUSTOM);
//        if (errors != SUCCESS) {
//            return ResponseVOUtil.fail(errors);
//        }
        //2.创建entity
        Admin admin = entityMapper.login(loginKey, BinaryUtil.encodeMd5(password));
        //3.响应校验
        if (admin == null) {
            throw new BusinessException(USER_LOGIN_ERROR);
        }
        if (admin.getStatus() == 0) {
            throw new BusinessException(SYSTEM_NO_ACCESS);
        }
        return admin;
    }

    @Override
    public List<Admin> selectListByPhone(String phone, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        criteria.andLike("createBy", "%" + phone + "%");
        example.orderBy("createDate").desc();

        return entityMapper.selectByExample(example);
    }

    @Override
    public Errors updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy) {
        int updateCount = entityMapper.updateByPassword(id, BinaryUtil.encodeMd5(oldPassword), BinaryUtil.encodeMd5(newPassword), updateBy);
        if (updateCount == 0) {
            throw new BusinessException(USER_OLD_PASSWORD_ERROR);
        }
        return SUCCESS;
    }

    private Errors validRequest(Admin recordDTO, BaseServiceMethodsEnum type) {
        Admin validRequest;
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case INSERT_SELECTIVE:
                criteria.andEqualTo("phone", recordDTO.getPhone());
                validRequest = entityMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return USER_MOBILE_EXISTS;
                }
                criteria.orEqualTo("account", recordDTO.getAccount());
                validRequest = entityMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return USER_USERNAME_SAME;
                }
                break;
            case CUSTOM:
//                if (StringUtil.isEmpty(recordDTO.getLoginKey()) || StringUtil.isEmpty(recordDTO.getPassword())) {
//                    return Errors.SYSTEM_REQUEST_PARAM_ERROR;
//                }
                break;
            case UPDATE_BY_PRIMARY_KEY_SELECTIVE:
                if (!StringUtil.isEmpty(recordDTO.getPhone())) {
                    criteria.andEqualTo("phone", recordDTO.getPhone());
                    validRequest = entityMapper.selectOneByExample(example);
                    if (validRequest != null && !validRequest.getId().equals(recordDTO.getId())) {
                        return USER_MOBILE_EXISTS;
                    }
                }
                break;
            default:
                return SUCCESS;
        }
        return SUCCESS;
    }
}
