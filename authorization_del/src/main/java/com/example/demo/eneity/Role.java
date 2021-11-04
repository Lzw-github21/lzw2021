package com.example.demo.eneity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lizhiwei
 * @date 2021/11/4 16:20
 */
public class Role implements GrantedAuthority {

    private int id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
