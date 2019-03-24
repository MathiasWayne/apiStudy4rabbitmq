package com.alibaba.rabbitmq4rabbitmq.rabbitmqConfig;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author:zhangl
 * @date:2019/3/24
 * @description:
 */
@Configuration
@ComponentScan
public class ConnectFactory {
    @Autowired
    private RabbitmqConfig config;

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setUsername(config.getUsername());
        connectionFactory.setPassword(config.getPassword());
        connectionFactory.setPort(config.getPort());
        connectionFactory.setHost(config.getHost());
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }
}
