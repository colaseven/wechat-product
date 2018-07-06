package com.caad.wechat.model;

import java.sql.Date;

import com.caadt.cln.common.util.DateUtil;

/**
 * 凭证实体类
 */
public class AccessToken {

    private String accessToken; // 接口访问凭证
    private int expiresIn; // 凭证有效期，单位：秒
    private long longExpAt; // 过期时间点，单位：毫秒
    private String strExpAt; // 过期时间点，文本

    public AccessToken(String accessToken, int expiresIn, long time) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.longExpAt = time + this.expiresIn * 1000;
        this.strExpAt = DateUtil.getDate("yyyy-M-d HH:mm:ss", new Date(this.longExpAt));
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= this.longExpAt;
    }

    public long getLongExpAt() {
        return longExpAt;
    }

    public String getStrExpAt() {
        return strExpAt;
    }
}
