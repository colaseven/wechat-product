package com.caad.wechat.model.viss.common;


public class OrganizaionAndAreaResult {


    private OrganizationWeChatSettingResult organizationInfo;

    private QueryAreaModel area;

    private String openId;

    private int subscribe;//是否关注过微信公众号

    private String errorMessage;//

    private String statement;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public OrganizationWeChatSettingResult getOrganizationInfo() {
        return organizationInfo;
    }

    public void setOrganizationInfo(OrganizationWeChatSettingResult organizationInfo) {
        this.organizationInfo = organizationInfo;
    }

    public QueryAreaModel getArea() {
        return area;
    }

    public void setArea(QueryAreaModel area) {
        this.area = area;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
}
