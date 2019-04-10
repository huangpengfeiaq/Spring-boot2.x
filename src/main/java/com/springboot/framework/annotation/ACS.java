package com.springboot.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制注解
 * 
 * @author leihe@uworks.cc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ACS {
  /**
   * 是否允许匿名访问
   */
  boolean allowAnonymous() default false;
}
