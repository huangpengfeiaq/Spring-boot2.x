package com.springboot.framework.service;

import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.Admin;

import java.util.List;

/**
 * 管理员操作
 *
 * @author huangpengfei
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 登陆
     *
     * @param loginKey 用户名或手机号
     * @param password 密码
     * @return ResponseVO<Admin>
     */
    Admin login(String loginKey, String password);

    /**
     * 列表查询（根据手机号）
     *
     * @param phone    手机号
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseVO
     */
    List<Admin> selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    /**
     * 更新（密码）
     *
     * @param id          管理员主键
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param updateBy    更新人
     * @return ResponseVO<Errors>
     */
    Errors updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
