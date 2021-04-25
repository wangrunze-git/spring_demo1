package com.springboot.chapter11.pojo;

import com.springboot.chapter5.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Alias("user")
public class User implements Serializable {

    private static final long serialVersionUID = 5429610570327061235L;

    public User(Long id, String userName, String note) {
        this.id = id;
        this.userName = userName;
        this.note = note;
    }

    public User() {
    }

    private Long id;
    private String userName;
    private SexEnum sex = null;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
