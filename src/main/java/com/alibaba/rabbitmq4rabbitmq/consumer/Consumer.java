package com.alibaba.rabbitmq4rabbitmq.consumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author:zhangl
 * @date:2019/3/24
 * @description: mq消费者
 */

public class Consumer {

    public static void main(String[] args) {
        /**创建连接工厂，并进行相应的配置*/
        /**创建连接工厂，并进行相应的配置*/
        ConnectionFactory factory=new ConnectionFactory();
        //设置端口
        factory.setHost("39.96.172.135");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        //进行连接
        Connection connection;
        Channel channel;
        try {
            connection = factory.newConnection();
            //通过connection建立channel
             channel = connection.createChannel();
             String exchangeType="topic";
             String exchangName="testTopic";
             String routingKey="test.#";
             //声明交换机
            channel.exchangeDeclare(exchangName,exchangeType,true,false,false,null);
            //声明消息队列
            String queueName="test001";
            channel.queueDeclare(queueName,true,false,false,null);
            //建立绑定关系
            channel.queueBind(queueName,exchangName,routingKey);
            //创建消费者
            QueueingConsumer  queueingConsumer=new QueueingConsumer(channel);
            //设置channel
            channel.basicConsume(queueName,true,queueingConsumer);
            //接受消息
            while (true){
                QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
                System.out.println("获取到的消息体内容："+new String(delivery.getBody()));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
