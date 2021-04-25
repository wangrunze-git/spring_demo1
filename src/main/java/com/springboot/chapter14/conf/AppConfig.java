package com.springboot.chapter14.conf;

import com.rabbitmq.client.Return;
import com.springboot.chapter10.converter.StringToUserConverter;
import com.springboot.chapter14.handler.UserHandler;
import com.springboot.chapter14.pojo.User;
import com.springboot.chapter14.repository.UserRepository;
import com.springboot.chapter14.validator.UserValidator;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

//实现Java8的接口WebFluxConfigure，该接口都是default方法
@Configuration
public class AppConfig implements WebFluxConfigurer {

    //注册Converter
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserConverter());
    }

    //定义String --> User类型转换器
    //@Bean//如果定义为Spring Bean，Spring Boot会自动识别为类型转换器
    public Converter<String, User> stringToUserConverter(){
        Converter<String, User> converter = new Converter<String, User>() {
            @Override
            public User convert(String src) {
                String[] strArr = src.split("-");
                User user = new User();
                Long id = Long.valueOf(strArr[0]);
                user.setId(id);
                user.setUserName(strArr[1]);
                user.setNote(strArr[2]);
                return user;
            }
        };
        return converter;
    }

    /*加入用户验证器，这里创建一个验证器，不能是多个，而这个验证器是全局性的，为各个控制器所共享*/
    @Override
    public Validator getValidator() {
        return new UserValidator();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                //注册资源，可以通过URI访问
            .addResourceHandler("/resources/static/**")
                //注册Spring资源，可以在Spring机制中访问
            .addResourceLocations("/public", "classpath:/static/")
                //缓存一年
            .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }




}
