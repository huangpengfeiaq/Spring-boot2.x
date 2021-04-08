package com.springboot.framework.service;

import com.springboot.framework.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户缓存，具体实现可以是session或redis中
 * 基础的控制器，如对Token的操作等..
 *
 * @author haungpengfei
 * @version 1.2.0325
 * @since 2019/1/10
 */
public interface RedisTokenService {
    /**
     * 创建token
     *
     * @param userVO 缓存用户
     * @return 缓存用户token值
     */
    String getToken(UserVO userVO);

    /**
     * 刷新用户
     *
     * @param token 缓存用户token值
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     *
     * @param userVO 缓存用户
     */
    void loginOff(UserVO userVO);

    /**
     * 用户退出登陆
     *
     * @param request http请求
     */
    void loginOff(HttpServletRequest request);

    /**
     * 获取用户信息
     *
     * @param token 缓存用户token值
     * @return 缓存用户信息
     */
    UserVO getUserInfoByToken(String token);

    /**
     * 获取缓存用户，不为空，重新设置缓存中用户的过期时间
     *
     * @param request http请求
     * @return 缓存用户信息
     */
    UserVO getSessionUser(HttpServletRequest request);

    /**
     * 获取真实ip
     *
     * @param request http请求
     * @return IP地址
     */
    String getRemoteIp(HttpServletRequest request);
}