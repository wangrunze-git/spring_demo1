package com.springboot.chapter8.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

//标识为MongoDB文档
@Document
public class User implements Serializable {

    private static final long serialVersionUID = -5243499839048136113L;

    //MongoDB文档编号，主键
    @Id
    private Long id;

    //在MongoDb中使用user_name保存属性
    @Field("user_name")
    private String userName = null;

    private String note = null;

    //角色列表,如果你只是想保存其引用，可以使用@DBRef标注，则它只会保存引用信息，而不是具体角色信息
    private List<Role> roles = null;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
