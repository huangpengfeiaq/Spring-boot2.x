package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dao.mapper.AdminMapper;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Override
    public ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy) {
        return ResponseEntityUtil.success(adminMapper.deleteByPrimaryKey(id, updateBy));
    }

    @Override
    public ResponseEntity<Integer> insertSelective(Admin record) {
        return ResponseEntityUtil.success(adminMapper.insertSelective(record));
    }

    @Override
    public ResponseEntity<Admin> login(String phone, String password) {
        return ResponseEntityUtil.success(adminMapper.login(phone, password));
    }

    @Override
    public ResponseEntity<Admin> selectByPrimaryKey(Integer id) {
        return ResponseEntityUtil.success(adminMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.selectList();
        PageInfo pageInfo = new PageInfo(admins);
        pageInfo.setList(admins);

        PageResponseBean page = new PageResponseBean<Admin>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy) {
        Admin record = new Admin();
        record.setId(id);
        record.setStatus(status);
        record.setUpdateBy(updateBy);
        return ResponseEntityUtil.success(adminMapper.updateByPrimaryKeySelective(record));
    }
}
