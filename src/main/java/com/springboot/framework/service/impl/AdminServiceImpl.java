package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.constant.BaseServiceMethodsEnum;
import com.springboot.framework.service.BaseService;
import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dao.mapper.AdminMapper;
import com.springboot.framework.dto.AdminDTO;
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

import static com.springboot.framework.constant.Errors.SUCCESS;

/**
 * 管理员业务处理类
 * > @Transactional事务，@Scheduled定时器
 *
 * @author huangpengfei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    private AdminMapper adminMapper;

    /**
     * 自定义定时任务，每天23点执行一次
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void customScheduled() {
        log.info("---------------- 定时任务 ----------------");
        log.info("customScheduled被执行了...");
    }

    @Override
    public ResponseVO<Admin> login(String loginKey, String password) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "login");
//        if (errors.code != 0) {
//            return ResponseVOUtil.fail(errors);
//        }
        //2.创建entity
        Admin admin = adminMapper.login(loginKey, BinaryUtil.encodeMd5(password));
        //3.响应校验
        if (admin == null) {
            return ResponseVOUtil.fail(Errors.USER_LOGIN_ERROR);
        }
        if (admin.getStatus() == 0) {
            return ResponseVOUtil.fail(Errors.SYSTEM_NO_ACCESS);
        }
        return ResponseVOUtil.success(admin);
    }

    @Override
    public PageResponseVO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        example.orderBy("createDate").desc();

        List<Admin> adminList = adminMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public PageResponseVO selectListByPhone(String phone, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        criteria.andLike("createBy", "%" + phone + "%");
        example.orderBy("createDate").desc();

        List<Admin> adminList = adminMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseVO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy) {
        int updateCount = adminMapper.updateByPassword(id, BinaryUtil.encodeMd5(oldPassword), BinaryUtil.encodeMd5(newPassword), updateBy);
        if (updateCount == 0) {
            return ResponseVOUtil.fail(Errors.USER_OLD_PASSWORD_ERROR);
        }
        return ResponseVOUtil.success(SUCCESS);
    }

    @Override
    public Errors validRequest(Admin recordDTO, BaseServiceMethodsEnum type) {
        Admin validRequest;
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case INSERT_SELECTIVE:
                criteria.andEqualTo("phone", recordDTO.getPhone());
                validRequest = adminMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return Errors.USER_MOBILE_EXISTS;
                }
                criteria.orEqualTo("account", recordDTO.getAccount());
                validRequest = adminMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return Errors.USER_USERNAME_SAME;
                }
                break;
            case CUSTOM :
//                if (StringUtil.isEmpty(recordDTO.getLoginKey()) || StringUtil.isEmpty(recordDTO.getPassword())) {
//                    return Errors.SYSTEM_REQUEST_PARAM_ERROR;
//                }
                break;
            case UPDATE_BY_PRIMARY_KEY_SELECTIVE:
                if (!StringUtil.isEmpty(recordDTO.getPhone())) {
                    criteria.andEqualTo("phone", recordDTO.getPhone());
                    validRequest = adminMapper.selectOneByExample(example);
                    if (validRequest != null && !validRequest.getId().equals(recordDTO.getId())) {
                        return Errors.USER_MOBILE_EXISTS;
                    }
                }
                break;
            default:
                return SUCCESS;
        }
        return SUCCESS;
    }
}
