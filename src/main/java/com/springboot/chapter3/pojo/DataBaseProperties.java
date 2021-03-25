package com.springboot.chapter3.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataBaseProperties {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseProperties.class);

    @Value("${database.driverName}")
    private String driverName = null;

    @Value("${database.url}")
    private String url = null;

    private String userName = null;

    private String password = null;

    public String getDriverName() {

        return driverName;
    }

    public void setDriverName(String driverName) {
        logger.info(driverName);
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        logger.info(url);
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    @Value("${database.userName}")
    public void setUserName(String userName) {
        logger.info(userName);
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @Value("${database.password}")
    public void setPassword(String password){
        logger.info(password);
        this.password = password;
    }
}
