package com.springboot.framework.controller.request;


/**
 * @author huangpengfei
 */
//@Data
public class UpdateByStatus {
    private Integer id;
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
