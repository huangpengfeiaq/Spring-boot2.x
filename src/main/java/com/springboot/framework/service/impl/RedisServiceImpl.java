package com.springboot.framework.service.impl;

import com.springboot.framework.config.AppConfig;
import com.springboot.framework.service.RedisService;
import com.springboot.framework.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 缓存业务
 *
 * @author haungpengfei
 * @version 1.0.0326
 * @date 2019年3月26日
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisUtil redisUtils;
    @Resource
    private AppConfig appConfig;

    @Override
    public void set(String key, int expireTime, Object value) {
        redisUtils.set(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, value, expireTime);
    }

    @Override
    public void delete(String key) {
        redisUtils.del(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
    }

    @Override
    public Object get(String key) {
        return redisUtils.get(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
    }

    @Override
    public Object refresh(String key, int expireTime) {
        Object value = redisUtils.get(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key);
        if (value != null) {
            redisUtils.set(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, value, expireTime);
        }
        return value;
    }

    /**
     * 缓存值+1，返回+1之后的值
     *
     * @param key key
     * @return 缓存值
     */
    protected long increment(String key) {
        return redisUtils.incr(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, 1);
    }

    /**
     * 缓存值-1，返回-1之后的值
     *
     * @param key key
     * @return 缓存值
     */
    protected long decrement(String key) {
        return redisUtils.decr(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + key, 1);
    }
}
