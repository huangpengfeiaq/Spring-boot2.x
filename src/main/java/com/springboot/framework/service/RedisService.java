package com.springboot.framework.service;

/**
 * 缓存业务
 *
 * @author haungpengfei
 * @version 1.0.0326
 * @date 2019年3月26日
 */
public interface RedisService {
    /**
     * 设置缓存
     *
     * @param key：关键字
     * @param exprieTime：过期时间，单位秒（例如exprieTime=30，为30秒）
     * @param value：值
     */
    void set(String key, int exprieTime, Object value);

    /**
     * 删除缓存
     *
     * @param key 键
     */
    void delete(String key);

    /**
     * 获取缓存值
     *
     * @param key 键
     * @return Object
     */
    Object get(String key);

    /**
     * 获得缓存中的数据并重置其过期时间.
     *
     * @param key        键
     * @param exprieTime 过期时间，单位秒（例如exprieTime=30，为30秒）
     * @return Object
     */
    Object refresh(String key, int exprieTime);
}
