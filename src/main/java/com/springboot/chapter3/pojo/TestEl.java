package com.springboot.chapter3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestEl {

    @Value("${database.driverName}")
    String driver;

    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime = null;

    @Value("#{'使用Spring EL赋值字符串'}")
    private String str = null;

    @Value("#{9.3E3}")
    private double d;

    @Value("#{3.14}")
    private float pi;

    @Value("#{dataBaseProperties.url}")
    private String otherBeanProp = null;

    @Value("#{dataBaseProperties.url?.toUpperCase()}")
    private String otherBeanprop1 = null;

    @Value("#{1+2}")
    private int run;

    @Value("#{3.13f == 3.14f}")
    private boolean piFlag;

    @Value("#{'aaa' eq 'aaa'}")
    private boolean strFlag;

    @Value("#{'aa' + 'bb'}")
    private String strApp = null;

    @Value("#{33 > 21 ? '大于' : '小于'}")
    private String resultDesc = null;

    public String getStrApp() {
        return strApp;
    }

    public void setStrApp(String strApp) {
        this.strApp = strApp;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public boolean isStrFlag() {
        return strFlag;
    }

    public void setStrFlag(boolean strFlag) {
        this.strFlag = strFlag;
    }

    public boolean isPiFlag() {
        return piFlag;
    }

    public void setPiFlag(boolean piFlag) {
        this.piFlag = piFlag;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public String getOtherBeanprop1() {
        return otherBeanprop1;
    }

    public void setOtherBeanprop1(String otherBeanprop1) {
        this.otherBeanprop1 = otherBeanprop1;
    }

    public String getOtherBeanProp() {
        return otherBeanProp;
    }

    public void setOtherBeanProp(String otherBeanProp) {
        this.otherBeanProp = otherBeanProp;
    }

    public float getPi() {
        return pi;
    }

    public void setPi(float pi) {
        this.pi = pi;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }
}
