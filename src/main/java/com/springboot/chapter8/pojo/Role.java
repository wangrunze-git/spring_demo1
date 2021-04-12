package com.springboot.chapter8.pojo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document //如果你只是在User中使用角色，没有别的场景使用了，那么你也可以不使用@Document标明对象为MongoDb的文档
public class Role implements Serializable {

    private static final long serialVersionUID = -445160012810113839L;

    private Long id;

    @Field("role_name")
    private String roleName = null;

    private String note = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
