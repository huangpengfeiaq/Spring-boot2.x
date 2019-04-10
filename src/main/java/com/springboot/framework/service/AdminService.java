package com.springboot.framework.service;

import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.util.ResponseEntity;

public interface AdminService {
    ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy);

    ResponseEntity<Integer> insertSelective(Admin record);

    ResponseEntity<Admin> login(String phone, String password);

    ResponseEntity<Admin> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy);
}
