package com.caad.wechat.model.viss.assessAgent;


import net.sf.json.JSONObject;

public class WeChatEntrustPaymentDto {

    private String id;

    private String boughtNumber;

    private String time;

    private String entrustPaymentIds;

    private String price;

    private String userOpenId;

    private String userName;

    private long organizationId;

    private String organizationName;

    private String companyId;

    private String companyName;

    private JSONObject weChatPayInfo;

    private String  entrustId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoughtNumber() {
        return boughtNumber;
    }

    public void setBoughtNumber(String boughtNumber) {
        this.boughtNumber = boughtNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEntrustPaymentIds() {
        return entrustPaymentIds;
    }

    public void setEntrustPaymentIds(String entrustPaymentIds) {
        this.entrustPaymentIds = entrustPaymentIds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public JSONObject getWeChatPayInfo() {
        return weChatPayInfo;
    }

    public void setWeChatPayInfo(JSONObject weChatPayInfo) {
        this.weChatPayInfo = weChatPayInfo;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }
}

