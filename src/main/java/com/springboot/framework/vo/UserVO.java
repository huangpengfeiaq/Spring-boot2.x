package com.springboot.framework.vo;

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
}
