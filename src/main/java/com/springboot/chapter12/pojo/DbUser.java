package com.springboot.chapter12.pojo;

import java.io.Serializable;

public class DbUser implements Serializable {

    private static final long serialVersionUID = -5469375183010086341L;

    private Long id;

    private String userName;

    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
