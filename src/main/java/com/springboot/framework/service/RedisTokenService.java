package com.springboot.framework.service;

import com.springboot.framework.dao.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface RedisTokenService {

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(Admin userInfo);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param userInfo
     */
    void loginOff(Admin userInfo);

    /**
     * 用户退出登陆
     * @param request
     */
    void loginOff(HttpServletRequest request);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    Admin getUserInfoByToken(String token);

    /**
     * 获取缓存用户，不为空，重新设置缓存中用户的过期时间
     *
     * @param request
     * @return
     */
    Admin getSessionUser(HttpServletRequest request);
}