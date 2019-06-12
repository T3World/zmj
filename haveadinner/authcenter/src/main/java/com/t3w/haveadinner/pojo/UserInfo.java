package com.t3w.haveadinner.pojo;

public class UserInfo {

    private String[] roles;
    private String[] resources;

    private String username;
    private String userId;
    private String token;

    public UserInfo() {
    }

    public UserInfo(User user) {
        if (user!=null){
            this.setUserId(String.valueOf(user.getId()));
            this.setUsername(user.getUsername());
        }
}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
