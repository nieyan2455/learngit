package com.ny.vo;

import java.util.List;

/**
 * 用户对象
 *
 *
 */
public class UserBean {
    private String token;
    private String username;
    private List<String> resources;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
