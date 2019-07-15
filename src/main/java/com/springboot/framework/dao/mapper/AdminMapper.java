package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author huangpengfei
 */
public interface AdminMapper extends Mapper<Admin> {
    /**
     * 登陆
     *
     * @param loginKey 管理员用户名或手机号
     * @param password 密码
     * @return 管理员
     */
    @Select("SELECT * FROM sys_admin WHERE status != -1 AND (phone = #{loginKey} OR account = #{loginKey}) AND password = #{password}")
    Admin login(@Param("loginKey") String loginKey, @Param("password") String password);

    /**
     * 登陆
     *
     * @param id          管理员id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param updateBy    更新者
     * @return 更新记录数
     */
    @Update("UPDATE sys_admin SET password = #{newPassword}, update_by = #{updateBy} WHERE id = #{id} AND password = #{oldPassword}")
    int updateByPassword(@Param("id") Integer id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword, @Param("updateBy") String updateBy);
}