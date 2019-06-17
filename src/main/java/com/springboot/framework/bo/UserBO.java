package com.springboot.framework.bo;

import com.springboot.framework.dao.pojo.Admin;
import lombok.Data;

/**
 * 缓存用户业务类，用于所有类型的用户缓存
 *
 * @author haungpengfei
 * @version 1.0
 * @since 2019/6/17
 */
@Data
public class UserBO {
    private Integer id;
    private String name;
    private String phone;
    private Byte status;
    private String accessToken;

    public UserBO() {
    }

    public UserBO(Admin record) {
        this.id = record.getId();
        this.name = record.getName();
        this.phone = record.getPhone();
        this.status = record.getStatus();
        this.accessToken = record.getAccessToken();
    }
}
