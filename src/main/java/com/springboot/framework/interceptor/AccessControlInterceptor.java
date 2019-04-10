package com.springboot.framework.interceptor;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.contants.Errors;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.service.RedisTokenService;
import com.springboot.framework.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限控制拦截器.
 *
 * @author leihe@uworks.cc
 */
@Component
public class AccessControlInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected RedisTokenService redisTokenService;

    private static final List<String> noLoginResources = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        {
            // swagger相关资源不需要登
            add("/swagger-ui.html");
            add("/configuration");
            add("/swagger-resources");
            add("/api-docs");
            add("/v2/api-docs");
            add("/webjars");
            add("/devicerequest/*");

            add("/admin/login");

//            add("/sms/*");

            add("/error");

        }
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不需要进行访问控制的资源过滤
        String uri = request.getRequestURI();
        for (String resource : noLoginResources) {
            if (uri.startsWith(resource)) {
                return true;
            }
        }
        if (handler instanceof HandlerMethod) {
            ACS acs = ((HandlerMethod) handler).getMethodAnnotation(ACS.class);
            // 判断是否允许匿名访问
            if (acs != null && acs.allowAnonymous()) {
                return true;
            }
        }
        // 缓存获取验证
        Admin user = redisTokenService.getSessionUser(request);
        if (user == null) {
            ExceptionUtil.throwException(Errors.SYSTEM_NOT_LOGIN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
