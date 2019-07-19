package com.springboot.framework.config;

import com.springboot.framework.interceptor.AccessControlInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Spring MVC 的辅助配置, 用来注册拦截器.
 *
 * @author jzsong@uworks.cc
 */
@Configuration
public class DefaultWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 访问权限控制
        AccessControlInterceptor accessControlInterceptor = applicationContext.getBean(AccessControlInterceptor.class);

        registry.addInterceptor(accessControlInterceptor);
        //registry.addInterceptor(accessControlInterceptor).addPathPatterns("/html/**");;
    }
}
