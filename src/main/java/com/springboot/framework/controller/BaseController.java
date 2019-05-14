package com.springboot.framework.controller;

import com.springboot.framework.constant.Const;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.service.RedisTokenService;
import com.springboot.framework.util.ExceptionUtil;
import com.springboot.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 基础的控制器，如对Token的操作等..
 *
 * @author haungpengfei
 * @version 1.2
 * @since 2019/1/10
 * @date 2019/5/8
 */
@RestController
@RequestMapping("/")
public abstract class BaseController {
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Resource
    protected RedisTokenService redisTokenService;

    /**
     * 获取真实ip
     */
    protected String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * protected
     */
    protected String generateAccessToken(HttpServletRequest request) {
        return request.getSession().getId() + StringUtil.uuidNotLine();
        // return request.getSession().getId();
    }

    /**
     * 获得 AccessToken
     */
    protected String getAccessToken(HttpServletRequest request) {
        return request.getHeader(Const.ACCESS_TOKEN_HEADER_NAME);
    }

    /**
     * 设置登录权限的token
     */
    protected void setAccessTokenAttribute(HttpServletRequest request, String accessToken) {
        request.setAttribute(Const.ACCESS_TOKEN_HEADER_NAME, accessToken);
    }

    /**
     * 缓存用户信息
     */
    protected void setSessionUser(HttpServletRequest request, Admin user) {
        redisTokenService.getToken(user);
    }

    protected Admin getSessionUser(HttpServletRequest request) {
        return redisTokenService.getSessionUser(request);
    }

    protected Admin getSessionUser(String accessToken) {
        return redisTokenService.getUserInfoByToken(accessToken);
    }

    protected void deleteSessionUser(HttpServletRequest request) {
        redisTokenService.loginOff(request);
    }

    protected void throwException(int code, String codeLabel) {
        ExceptionUtil.throwException(code, codeLabel);
    }

    protected void throwException(Errors error) {
        ExceptionUtil.throwException(error);
    }
}
