package com.alibaba.rabbitmq4rabbitmq.rabbitmqConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author:zhangl
 * @date:2019/3/24
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "rabbitmq")
@PropertySource("classpath:properties/rabbitmq_connect.properties")
@Data
public class RabbitmqConfig {
    private String username;
    private String password;
    private Integer port;
    private String host;
}
