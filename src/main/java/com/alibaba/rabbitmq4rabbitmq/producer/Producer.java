package com.alibaba.rabbitmq4rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zhangl
 * @date:2019/3/24
 * @description: mq生产者
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        /**创建连接工厂，并进行相应的配置*/
        ConnectionFactory factory=new ConnectionFactory();
        //设置端口
        factory.setHost("39.96.172.135");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        //进行连接
        Connection connection = null;
        Channel channel =null;
        try {
            connection = factory.newConnection();
            //通过connection建立channel
             channel = connection.createChannel();
             String exchangeName="testTopic";
             String routingKey="test.topic";
            //通过channel发送信息
             //第一个参数是exchange 第二个参数是routingkey 第四个参数是发送打的内容
            channel.basicPublish(exchangeName,routingKey,null,"test".getBytes());
        } catch (IOException e) {
            e.getCause();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if (channel != null) {
            channel.close();
        }
        if (connection != null) {
            connection.close();
        }

    }

}
