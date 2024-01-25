package org.bohan.news;

import com.alibaba.fastjson.JSONObject;

import java.sql.Date;

public class User {
    private int uid;
    private String mobileNumber;
    private String email;
    private String password;
    private String avatarUrl;
    private String nickname;
    private JSONObject favorites;
    private JSONObject newsPreferences;
    private Date lastLoginTime;

    public User() {
        this.uid = 0;
        this.mobileNumber = null;
        this.email = null;
        this.password = null;
        this.avatarUrl = null;
        this.nickname = null;
        this.favorites = null;
        this.newsPreferences = null;
        this.lastLoginTime = null;
    }

    public User(String email, String password, String nickname) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public JSONObject getFavorites() {
        return favorites;
    }

    public void setFavorites(JSONObject favorites) {
        this.favorites = favorites;
    }

    public JSONObject getNewsPreferences() {
        return newsPreferences;
    }

    public void setNewsPreferences(JSONObject newsPreferences) {
        this.newsPreferences = newsPreferences;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
