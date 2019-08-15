package com.springboot.framework.service;

import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dto.AdminDTO;

/**
 * 管理员操作
 *
 * @author huangpengfei
 */
public interface AdminService extends BaseService<Admin, AdminDTO> {
    /**
     * 登陆
     *
     * @param adminDTO 管理员传输对象
     * @return ResponseVO<Admin>
     */
    ResponseVO<Admin> login(AdminDTO adminDTO);

    /**
     * 列表查询（根据手机号）
     *
     * @param phone    手机号
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseVO
     */
    PageResponseVO selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    /**
     * 更新（密码）
     *
     * @param id          管理员主键
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param updateBy    更新人
     * @return ResponseVO<Errors>
     */
    ResponseVO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
