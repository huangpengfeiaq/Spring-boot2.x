package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    @Select("SELECT * FROM sys_admin WHERE status != -1 AND (phone = #{loginKey} OR account = #{loginKey}) AND password = #{password}")
    Admin login(@Param("loginKey") String loginKey, @Param("password") String password);

    @Update("UPDATE sys_admin SET password = #{newPassword}, update_by = #{updateBy} WHERE id = #{id} AND password = #{oldPassword}")
    int updateByPassword(@Param("id") Integer id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword, @Param("updateBy") String updateBy);
}