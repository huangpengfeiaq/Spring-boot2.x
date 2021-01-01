package com.springboot.framework.config;

import com.springboot.framework.interceptor.CrossDomainFilter;
import com.springboot.framework.config.model.ObjectStorageClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 生成特殊bean的工厂bean, 有些第三方的Bean没有标注@component, 需要手工初始化.
 * >@Configuration
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 * Full模式(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 * Lite模式(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 * 组件依赖必须使用Full模式默认。其他默认是否Lite模式
 * <p>
 * >@EnableConfigurationProperties
 * 1、开启配置绑定功能
 * 2、把这个组件自动注册到容器中
 *
 * @author huangpengfei
 */
@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties
public class AppBeanFactory {
    @Resource
    private AppConfig appConfig;
    @Resource
    private ObjectStorageConfig objectStorageConfig;

    private ObjectStorageClient getClient(String endpoint) {
        return new ObjectStorageClient(objectStorageConfig.getAccessKeyId(), objectStorageConfig.getAccessKeySecret(), endpoint);
    }

    /**
     * 存储-下载
     *
     * @return COSClient
     */
    @Bean(name = "downloadClient")
    public ObjectStorageClient downloadClient() {
        return getClient(objectStorageConfig.getDownloadEndpoint());
    }

    /**
     * 存储-上传
     *
     * @return COSClient
     */
    @Bean(name = "uploadClient")
    public ObjectStorageClient uploadClient() {
        return getClient(objectStorageConfig.getUploadEndpoint());
    }

    /**
     * 注册跨域支持过滤器
     */
    @Bean
    public FilterRegistrationBean registerCrossDomainFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
        // 设置是否允许跨域访问
        crossDomainFilter.setAllowCrossDomain(appConfig.getAllowCrossDomainAccess());
        registrationBean.setFilter(crossDomainFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
