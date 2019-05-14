package com.springboot.framework.service;

import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.util.ResponseEntity;

public interface AdminService {
    ResponseEntity<Errors> deleteByPrimaryKey(AdminDTO recordDTO);

    ResponseEntity<Errors> insertSelective(AdminDTO recordDTO);

    ResponseEntity<Admin> login(AdminDTO recordDTO);

    ResponseEntity<Admin> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    ResponseEntity<Integer> selectCount();

    ResponseEntity<Errors> updateByPrimaryKeySelective(AdminDTO recordDTO);

    ResponseEntity<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
