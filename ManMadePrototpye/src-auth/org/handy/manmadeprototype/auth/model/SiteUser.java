package org.handy.manmadeprototype.auth.model;

/**
 * Created by zhongming on 15/12/19.
 */
public class SiteUser {

    private long id; // 用户ID
    private String username; // 用户名
    private String password; // 密码
    private String authorities; //鉴权信息
    private boolean enabled; // 禁封状态

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
