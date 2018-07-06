package com.caad.wechat.model.viss.common;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class PassportUserModelExt {
    @JsonProperty("id")
    private long id;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ActiveTime")
    private Date activeTime;
    @JsonProperty("AppToken")
    private String appToken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }
}
