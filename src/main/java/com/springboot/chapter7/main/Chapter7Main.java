package com.springboot.chapter7.main;

import com.example.demo.DemoApplication;
import com.springboot.chapter7.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@SpringBootApplication(scanBasePackages = {"com.springboot.chapter8"})
@EnableCaching
//指定扫描的MyBatis Mapper
@MapperScan(basePackages = "com.springboot.chapter7", annotationClass = Repository.class)
public class Chapter7Main {

    //Redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    //Redis消息监听器
    @Autowired
    private MessageListener redisMsgListener = null;

    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate = null;

    /**
     * 创建任务池，运行线程等待处理Redis的消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //Redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道，名称为topic1
        ChannelTopic topic = new ChannelTopic("topic1");
        //使用监听器监听Redis的消息
        container.addMessageListener(redisMsgListener, topic);
        return container;
    }

    //定义自定义后初始化方法
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    public static void main(String[] args) {
        /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "field", "hvalue");*/
        SpringApplication.run(Chapter7Main.class, args);
    }

    //设置RedisTemplate的序列化器
    private void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    //spring中配置，springboot会自动配置
    /**
     * 需要处理底层的转换规则，如果不考虑改写底层，尽量不使用它
     * @param redisTemplate
     *//*
    public void useRedisCallback(RedisTemplate redisTemplate){
       *//* redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection rc) throws DataAccessException {
                rc.set("key1".getBytes(), "value1".getBytes());
                rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
                return null;
            }
        });*//*
       //lambda版本
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }

    *//**
     * 高级接口，比较友好，一般情况下，优先使用它
     * @param redisTemplate
     *//*
    public void useSessionCallback(RedisTemplate redisTemplate){
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations ro) throws DataAccessException {
                ro.opsForValue().set("key1", "value1");
                ro.opsForHash().put("hash", "key1", "value1");
                return null;
            }
        });
        //FIXME lambda版本报错，不支持lambda？
        *//*redisTemplate.execute((RedisOperations ro) -> {
            ro.opsForValue().set("key1", "value1");
            ro.opsForHash().put("hash", "key1", "value1");
            return null;
        });*//*
    }*/
}
