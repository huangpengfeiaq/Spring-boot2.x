package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 */
@Data
public class UpdateByStatus {
    private Integer id;
    private Byte status;
}
