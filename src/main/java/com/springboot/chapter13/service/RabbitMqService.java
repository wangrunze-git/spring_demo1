package com.springboot.chapter13.service;

import com.springboot.chapter11.pojo.User;

public interface RabbitMqService {

    //发送字符串消息
    public void sendMsg(String msg);

    //发送用户消息
    public void sendUser(User user);

}
