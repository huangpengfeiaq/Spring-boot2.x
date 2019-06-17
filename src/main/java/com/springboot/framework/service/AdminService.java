package com.springboot.framework.service;

import com.springboot.framework.constant.Errors;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.bo.ResponseBO;

public interface AdminService {
    ResponseBO<Errors> deleteByPrimaryKey(AdminDTO recordDTO);

    ResponseBO<Errors> insertSelective(AdminDTO recordDTO);

    ResponseBO<Admin> login(AdminDTO recordDTO);

    ResponseBO<Admin> selectByPrimaryKey(Integer id);

    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    PageResponseBO selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    ResponseBO<Integer> selectCount();

    ResponseBO<Errors> updateByPrimaryKeySelective(AdminDTO recordDTO);

    ResponseBO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
