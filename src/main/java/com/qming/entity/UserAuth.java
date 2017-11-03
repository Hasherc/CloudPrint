package com.qming.entity;

import java.io.Serializable;
import java.util.Date;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author qming
 * @ 17-8-7
 */
public class UserAuth implements Serializable {

    private static final long serialVersionUID = -6680722794850378666L;

    private String userUuid;
    private String email;
    private String phoneNum;
    private String password;
    private Date lastLoginTime;
    private int status;

    public UserAuth() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
