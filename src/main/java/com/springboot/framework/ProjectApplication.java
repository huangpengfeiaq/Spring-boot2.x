package com.springboot.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 主程序类
 * > @SpringBootApplication：这是一个SpringBoot应用
 * > @MapperScan 扫描mapper映射包路径
 * > @EnableTransactionManagement //开启事务管理
 * > @EnableScheduling //开启定时任务
 * > @EnableCaching //开启缓存管理
 *
 * @author huangpengfei
 */
@MapperScan(value = "com.springboot.framework.dao.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}