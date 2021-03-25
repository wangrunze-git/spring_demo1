package com.springboot.chapter3.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorExample implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanPostProcessorExample.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("BeanPostProcessor调用postProcessorBeforInitialization方法，参数【" +
                bean.getClass().getSimpleName() + "】【" + beanName + "】");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("BeanPostProcessor调用postProcessAfterInitialization方法，参数【" +
                bean.getClass().getSimpleName() + "】【" + beanName + "】");
        return bean;
    }
}
