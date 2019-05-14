package com.springboot.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置文件
 * @author LJH
 * @version 1.0
 * @date 2018/1/17 16:51
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用请求地址
     */
    private String appHost;

    /**
     * 是否允许跨域
     */
    private Boolean allowCrossDomainAccess;
    /**
     * 是否允许生成API文档
     */
    private Boolean allowGenerateApi;

    /**
     * 环境：dev:开发;test:测试;prod:生产
     */
    private String env;

    /**
     * 腾讯地图key
     */
    private String tencentMapKey;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppHost() {
        return appHost;
    }

    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    public Boolean getAllowCrossDomainAccess() {
        return allowCrossDomainAccess;
    }

    public void setAllowCrossDomainAccess(Boolean allowCrossDomainAccess) {
        this.allowCrossDomainAccess = allowCrossDomainAccess;
    }

    public Boolean getAllowGenerateApi() {
        return allowGenerateApi;
    }

    public void setAllowGenerateApi(Boolean allowGenerateApi) {
        this.allowGenerateApi = allowGenerateApi;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTencentMapKey() {
        return tencentMapKey;
    }

    public void setTencentMapKey(String tencentMapKey) {
        this.tencentMapKey = tencentMapKey;
    }
}
