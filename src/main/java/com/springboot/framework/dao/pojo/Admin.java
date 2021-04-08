package com.springboot.framework.dao.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huangpengfei
 */
@Data
@Table(name = "sys_admin")
public class Admin implements Serializable {
    public static final long serialVersionUID = 641655770L;

    @Id
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
    @Transient
    private String accessToken;
}