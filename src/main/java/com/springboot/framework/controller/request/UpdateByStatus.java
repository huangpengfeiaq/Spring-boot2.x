package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class UpdateByStatus {
    private Integer id;
    private Byte status;
}
