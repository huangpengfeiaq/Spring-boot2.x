package com.springboot.framework.service;

import com.springboot.framework.bo.UserBO;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础的控制器，如对Token的操作等..
 *
 * @author haungpengfei
 * @version 1.2.0325
 * @date 2019年3月25日
 * @Description: 用户缓存，具体实现可以是session或redis中
 * @since 2019/1/10
 */
public interface RedisTokenService {

    /**
     * 创建token
     */
    String getToken(UserBO userBOInfo);

    /**
     * 刷新用户
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     */
    void loginOff(UserBO userBOInfo);

    /**
     * 用户退出登陆
     */
    void loginOff(HttpServletRequest request);

    /**
     * 获取用户信息
     */
    UserBO getUserInfoByToken(String token);

    /**
     * 获取缓存用户，不为空，重新设置缓存中用户的过期时间
     */
    UserBO getSessionUser(HttpServletRequest request);

    /**
     * 获取真实ip
     */
    String getRemoteIP(HttpServletRequest request);
}