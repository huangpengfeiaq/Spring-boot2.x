package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {
    @Update("UPDATE sys_admin SET status = -1, update_by = #{updateBy} WHERE id = #{id}")
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("updateBy") String updateBy);

    int insert(Admin record);

    int insertSelective(Admin record);

    @Select("SELECT * FROM sys_admin WHERE phone = #{phone} AND password = #{password}")
    Admin login(@Param("phone") String phone, @Param("password") String password);

    Admin selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM sys_admin WHERE status != -1 ORDER BY create_date DESC")
    List<Admin> selectList();

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

}