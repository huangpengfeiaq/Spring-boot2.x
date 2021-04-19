package com.springboot.framework.vo;

import com.springboot.framework.dao.pojo.Admin;

/**
 * 缓存用户业务类，用于所有类型的用户缓存
 *
 * @author haungpengfei
 * @version 1.0
 * @since 2019/6/17
 */
//@Data
public class UserVO {
    private Integer id;
    private String name;
    private String phone;
    private Byte status;
    private String accessToken;

    public UserVO() {
    }

    public UserVO(Admin record) {
        this.id = record.getId();
        this.name = record.getName();
        this.phone = record.getPhone();
        this.status = record.getStatus();
        this.accessToken = record.getAccessToken();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
