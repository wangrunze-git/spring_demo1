package com.springboot.chapter13.service.impl;

import com.springboot.chapter11.pojo.User;
import com.springboot.chapter13.service.RabbitMqService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 这个类实现了RabbitMqService的confirm方法，换句话说，这个类可以作为RabbitMQ的生产者的回调类。
 * 其中注入了RabbitTemplate对象，这个对象是由Spring Boot通过自动配置生成的，并不需要自行处理。
 * RabbitTemplate的convertAndSend方法是转换和发送消息，与JmsTemplate一样，这个方法也是先转换消息，这里的转换消息是通过SimpleMessageConverter对象转换
 * 这个类也是RabbitTemplate默认的转换类，如有需要可以改变它
 * 方法里设置了msgRouting的路径，它就是消息队列的名称
 * 所以消息最终会发送到这个队列中，等待监听它的消费者进行消费
 */
@Service
public class RabbitMqServiceimpl implements RabbitTemplate.ConfirmCallback, RabbitMqService{

    @Value("${rabbitmq.queue.msg}")
    private String msgRouting = null;

    @Value("${rabbitmq.queue.user}")
    private String userRouting = null;

    //注入由Spring Boot自动配置的RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate = null;

    //发送消息
    @Override
    public void sendMsg(String msg) {
        System.out.println("发送消息：【" + msg + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        //发送消息，通过msgRouting确定队列
        rabbitTemplate.convertAndSend(msgRouting, msg);
    }

    @Override
    public void sendUser(User user) {
        System.out.println("发送用户消息【" + user + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(userRouting, user);
    }

    //回调确认方法


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            System.out.println("消息成功消费");
        }else {
            System.out.println("消息消费失败：" + cause);
        }
    }
}
