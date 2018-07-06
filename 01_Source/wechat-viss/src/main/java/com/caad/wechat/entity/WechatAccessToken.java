package com.caad.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_wechatAccessToken")
public class WechatAccessToken implements Serializable {

    private static final long serialVersionUID = -2169435265888204832L;

    @Id
    private String appId;
    @Column(length = 500)
    private String access_token;
    @Column(length = 11)
    private int expires_in;
    @Column(length = 100)
    private long last_time;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public long getLast_time() {
        return last_time;
    }

    public void setLast_time(long last_time) {
        this.last_time = last_time;
    }
}
