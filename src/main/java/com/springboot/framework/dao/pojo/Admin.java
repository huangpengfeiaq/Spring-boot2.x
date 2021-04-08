package com.springboot.framework.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huangpengfei
 * >@EqualsAndHashCode 当使用@Data注解时，则有了@EqualsAndHashCode注解，那么就会在此类中存在equals(Object other) 和 hashCode()方法，且不会使用父类的属性，这就导致了可能的问题。
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sys_admin")
public class Admin extends BaseEntity implements Serializable {
    public static final long serialVersionUID = 641655770L;

    @Id
    private Integer id;
    private String account;
    private String password;
    private String phone;
    private String name;
    @Transient
    private String accessToken;
}