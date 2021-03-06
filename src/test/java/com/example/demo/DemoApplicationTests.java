package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class DemoApplicationTests {

    private final static Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Test
    void contextLoads() {
        log.info("info");
        log.error("error1111111");
        log.warn("warn");
    }

}
