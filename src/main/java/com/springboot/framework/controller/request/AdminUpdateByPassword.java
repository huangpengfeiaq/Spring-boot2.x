package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class AdminUpdateByPassword {
    private String oldPassword;
    private String newPassword;
}
