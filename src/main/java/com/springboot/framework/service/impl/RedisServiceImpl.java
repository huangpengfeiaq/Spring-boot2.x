package com.springboot.framework.service.impl;

import com.springboot.framework.config.AppConfig;
import com.springboot.framework.service.RedisService;
import com.springboot.framework.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author haungpengfei
 * @version 1.0.0326
 * @date 2019年3月26日
 * @Description: 缓存业务
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisUtil redisUtils;
    @Resource
    private AppConfig appConfig;

    /**
     * 设置缓存
     *
     * @param key
     * @param exprieTime：过期时间，单位秒（例如exprieTime=30，为30秒）
     * @param value：值
     */
    @Override
    public void set(String key, int exprieTime, Object value) {
        redisUtils.set(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, value, exprieTime);
    }

    /**
     * 删除
     */
    @Override
    public void delete(String key) {
        redisUtils.del(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
    }

    /**
     * 获取缓存值
     */
    @Override
    public Object get(String key) {
        return redisUtils.get(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
    }

    /**
     * 获得缓存中的数据并重置其过期时间.
     */
    @Override
    public Object refresh(String key, int exprieTime) {
        Object value = redisUtils.get(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
        if (value != null) {
            redisUtils.set(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, value, exprieTime);
        }
        return value;
    }

    /**
     * 缓存值+1，返回+1之后的值
     *
     * @param key
     * @return
     */
    protected long incr(String key) {
        return redisUtils.incr(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, 1);
    }

    /**
     * 缓存值-1，返回-1之后的值
     *
     * @param key
     * @return
     */
    protected long decr(String key) {
        return redisUtils.decr(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, 1);
    }
}
