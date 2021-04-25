package com.springboot.chapter13.main;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.util.Password;

import java.net.PasswordAuthentication;

@SpringBootApplication(scanBasePackages = "com.springboot.chapter13")
public class Chapter13Application extends WebSecurityConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Chapter13Application.class, args);
    }



    /*创建两个RabbitMQ队列,这里Spring Boot的机制会自动注册这两个队列，所以并不需要自己做进一步的绑定*/
    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQuqueName = null;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQuqueName = null;

    @Bean
    public Queue createQueueMsg(){
        //创建字符串消息队列，boolean值代表是否持久化消息
        return new Queue(msgQuqueName, true);
    }

    @Bean
    public Queue createQueueUser(){
        //创建用户消息队列，boolean值代表是否持久化消息
        return new Queue(userQuqueName, true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码加密器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //加入三个内存用户，密码分别为加密后的"p1","p2","p3"
        //可以通过passwordEncoder.encode("p1")这样获得加密后的密码
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("user1")
                .password(passwordEncoder.encode("p1"))
                .roles("USER").and().withUser("user2")
                .password(passwordEncoder.encode("p2"))
                .roles("ADMIN").and().withUser("user3")
                .password(passwordEncoder.encode("p3"))
                .roles("USER");
    }
}
