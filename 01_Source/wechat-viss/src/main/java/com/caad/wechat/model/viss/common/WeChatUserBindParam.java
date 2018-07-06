package com.caad.wechat.model.viss.common;


public class WeChatUserBindParam {

    /// <summary>
    /// 机构Id
    /// </summary>
    private long organizationId;

    /// <summary>
    /// 机构名称
    /// </summary>
    private String organizationName;

    /// <summary>
    /// 公众号AppId
    /// </summary>
    private String appId;

    /// <summary>
    /// 公众号名称
    /// </summary>
    private String appName;

    /// <summary>
    /// 用户OpenId
    /// </summary>
    private String openId;

    /// <summary>
    /// 微信昵称
    /// </summary>
    private String nickName;

    /// <summary>
    /// 公司名称
    /// </summary>
    private String companyName;

    /// <summary>
    /// 绑定用户名称
    /// </summary>
    private String name;

    /// <summary>
    /// 手机
    /// </summary>
    private String phone;

    /// <summary>
    /// 岗位
    /// </summary>
    private String job;


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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
