package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 */
@Data
public class AdminDTO {
    private Integer id;
    private String account;
    private String password;
    private String phone;
    private String name;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    /**
     * 附加参数
     */
    private String loginKey;

    public AdminDTO() {
    }

    /**
     * 删除
     */
    public AdminDTO(Integer id, String updateBy) {
        this.id = id;
        this.updateBy = updateBy;
    }

    /**
     * 新增
     */
    public AdminDTO(String account, String password, String phone, String name, String createBy) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.createBy = createBy;
    }

    /**
     * 登陆
     */
    public AdminDTO(String loginKey, String password) {
        this.password = password;
        this.loginKey = loginKey;
    }

    /**
     * 更新
     */
    public AdminDTO(Integer id, String password, String phone, String name, String updateBy, Byte status) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.updateBy = updateBy;
        this.status = status;
    }
}
