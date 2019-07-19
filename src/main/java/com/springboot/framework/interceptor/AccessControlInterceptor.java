package com.springboot.framework.interceptor;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.service.RedisTokenService;
import com.springboot.framework.utils.ExceptionUtil;
import com.springboot.framework.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限控制拦截器.
 *
 * @author huangpengfei
 */
@Component
public class AccessControlInterceptor extends HandlerInterceptorAdapter {
    @Resource
    protected RedisTokenService redisTokenService;

    private static final List<String> NO_LOGIN_RESOURCES = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        {
            // swagger相关资源不需要拦截
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

    /**
     * 在业务之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不需要进行访问控制的资源过滤
        String uri = request.getRequestURI();
        for (String resource : NO_LOGIN_RESOURCES) {
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
        UserVO userVO = redisTokenService.getSessionUser(request);
        if (userVO == null) {
            ExceptionUtil.throwException(Errors.SYSTEM_NOT_LOGIN);
        }
        return true;
    }

    /**
     * 在业务之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在生命周期完结后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
