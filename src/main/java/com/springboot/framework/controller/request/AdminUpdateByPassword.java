package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 */
@Data
public class AdminUpdateByPassword {
    private String oldPassword;
    private String newPassword;
}
