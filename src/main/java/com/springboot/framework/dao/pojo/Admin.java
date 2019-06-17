package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.AdminDTO;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_admin")
public class Admin implements Serializable {
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

    public Admin() {
    }

    public Admin(AdminDTO adminDTO) {
        this.id = adminDTO.getId();
        this.account = adminDTO.getAccount();
        this.password = adminDTO.getPassword();
        this.phone = adminDTO.getPhone();
        this.name = adminDTO.getName();
        this.createBy = adminDTO.getCreateBy();
        this.createDate = adminDTO.getCreateDate();
        this.updateBy = adminDTO.getUpdateBy();
        this.updateDate = adminDTO.getUpdateDate();
        this.status = adminDTO.getStatus();
    }
}