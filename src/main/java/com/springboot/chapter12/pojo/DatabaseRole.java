package com.springboot.chapter12.pojo;

import java.io.Serializable;

public class DatabaseRole implements Serializable {

    private static final long serialVersionUID = -113026268369442616L;

    private Long id;

    private String roleName;

    public DatabaseRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

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
}
