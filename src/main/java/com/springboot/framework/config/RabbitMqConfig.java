package com.springboot.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 该类初始化创建队列、转发器，并把队列绑定到转发器
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/21 11:18
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 消息队列声明
     *
     * @return Queue
     */
    @Bean
    public Queue queue1() {
        // true表示持久化该队列
        return new Queue("hello.queue1", true);
    }

    @Bean
    public Queue queue2() {
        return new Queue("hello.queue2", true);
    }

    /**
     * 声明交互器
     *
     * @return TopicExchange
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 消息绑定
     *
     * @return Binding
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }
}
