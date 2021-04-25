package com.springboot.chapter14.pojo;

import com.springboot.chapter14.enumeration.SexEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

//标识为MongoDB文档
@Document
public class User implements Serializable {

    private static final long serialVersionUID = 8024875717817721097L;

    @Id
    private Long id;


    //在MongoDB中使用user_name保存属性
    @Field("user_name")
    private String userName;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
