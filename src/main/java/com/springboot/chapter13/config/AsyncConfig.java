package com.springboot.chapter13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@PropertySource(value = "classpath:application.properties")
@EnableScheduling
//使用STOMP协议
@EnableWebSocketMessageBroker
public class AsyncConfig implements AsyncConfigurer, WebSocketMessageBrokerConfigurer {

    //定义线程池
    @Override
    public Executor getAsyncExecutor(){
        //定义线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(10);
        //线程池最大线程数
        taskExecutor.setMaxPoolSize(30);
        //线程队列最大线程数
        taskExecutor.setQueueCapacity(2000);
        //初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    //创建服务端点
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    //注册服务器端点
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //增加一个聊天服务端点
        registry.addEndpoint("/socket").withSockJS();
        //增加一个用户服务端点
        registry.addEndpoint("/wsuser").withSockJS();
    }

    //定义服务端点请求和订阅前缀
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端订阅路径前缀
        registry.enableSimpleBroker("/sub", "queue");
        //服务端请求前缀
        registry.setApplicationDestinationPrefixes("/request");
    }
}
