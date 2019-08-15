package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
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

/**
 * 管理员业务处理类
 * > @Transactional事务，@Scheduled定时器
 *
 * @author huangpengfei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements BaseService<Admin, AdminDTO>, AdminService {
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
    public ResponseVO<Errors> deleteByPrimaryKey(AdminDTO recordDTO) {
        //2.创建entity
        Admin record = new Admin(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (adminMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseVOUtil.fail("删除失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseVO<Errors> insertSelective(AdminDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "insertSelective");
        if (errors.code != 0) {
            return ResponseVOUtil.fail(errors);
        }
        //2.创建entity
        Admin record = new Admin(recordDTO);
        record.setPassword(BinaryUtil.encodeMd5(record.getPassword()));
        //3.响应校验
        if (adminMapper.insertSelective(record) == 0) {
            return ResponseVOUtil.fail("添加失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseVO<Admin> login(AdminDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "login");
        if (errors.code != 0) {
            return ResponseVOUtil.fail(errors);
        }
        //2.创建entity
        Admin admin = adminMapper.login(recordDTO.getLoginKey(), BinaryUtil.encodeMd5(recordDTO.getPassword()));
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
    public ResponseVO<Admin> selectByPrimaryKey(Integer id) {
        return ResponseVOUtil.success(adminMapper.selectByPrimaryKey(id));
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
    public ResponseVO<Integer> selectCount() {
        Admin record = new Admin();
        record.setStatus((byte) 1);
        return ResponseVOUtil.success(adminMapper.selectCount(record));
    }

    @Override
    public ResponseVO<Errors> updateByPrimaryKeySelective(AdminDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
        if (errors.code != 0) {
            return ResponseVOUtil.fail(errors);
        }
        //2.创建entity
        Admin admin = new Admin(recordDTO);
        //3.响应校验
        if (adminMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseVOUtil.fail("更新失败");
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseVO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy) {
        int updateCount = adminMapper.updateByPassword(id, BinaryUtil.encodeMd5(oldPassword), BinaryUtil.encodeMd5(newPassword), updateBy);
        if (updateCount == 0) {
            return ResponseVOUtil.fail(Errors.USER_OLD_PASSWORD_ERROR);
        }
        return ResponseVOUtil.success(Errors.SUCCESS);
    }

    private Errors validRequest(AdminDTO recordDTO, String type) {
        Admin validRequest;
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case "insertSelective":
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
            case "login":
                if (StringUtil.isEmpty(recordDTO.getLoginKey()) || StringUtil.isEmpty(recordDTO.getPassword())) {
                    return Errors.SYSTEM_REQUEST_PARAM_ERROR;
                }
                break;
            case "updateByPrimaryKeySelective":
                if (!StringUtil.isEmpty(recordDTO.getPhone())) {
                    criteria.andEqualTo("phone", recordDTO.getPhone());
                    validRequest = adminMapper.selectOneByExample(example);
                    if (validRequest != null && !validRequest.getId().equals(recordDTO.getId())) {
                        return Errors.USER_MOBILE_EXISTS;
                    }
                }
                break;
            default:
                return Errors.SUCCESS;
        }
        return Errors.SUCCESS;
    }
}
