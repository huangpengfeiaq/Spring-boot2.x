package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 */
@Data
public class AdminUpdateByPrimaryKey {
    private Integer id;
    private String password;
    private String phone;
    private String name;
    private Byte status;
}
