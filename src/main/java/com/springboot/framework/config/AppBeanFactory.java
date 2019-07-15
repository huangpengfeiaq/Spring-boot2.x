package com.springboot.framework.config;

import com.springboot.framework.interceptor.CrossDomainFilter;
import com.springboot.framework.model.ObjectStorageClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 生成特殊bean的工厂bean, 有些第三方的Bean没有标注@component, 需要手工初始化.
 *
 * @author huangpengfei
 */
@Configuration
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
