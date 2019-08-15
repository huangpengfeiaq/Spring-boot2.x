package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 */
@Deprecated
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
     * 登陆
     */
    public AdminDTO(String loginKey, String password) {
        this.password = password;
        this.loginKey = loginKey;
    }
}
