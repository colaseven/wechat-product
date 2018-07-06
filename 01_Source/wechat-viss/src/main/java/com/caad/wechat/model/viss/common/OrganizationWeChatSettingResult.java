package com.caad.wechat.model.viss.common;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class OrganizationWeChatSettingResult {

    /// <summary>
    /// 估价机构id
    /// </summary>
    @JsonProperty("OrganizationId")
    private long organizationId;

    /// <summary>
    /// 机构名称
    /// </summary>
    @JsonProperty("OrganizationName")
    private String organizationName;

    /// <summary>
    /// 机构logo图标地址
    /// </summary>
    @JsonProperty("PropertyTypeId")
    private String propertyTypeId;

    /// <summary>
    /// 联系电话
    /// </summary>
    @JsonProperty("Telephone")
    private String telephone;

    /// <summary>
    /// 登录用户
    /// </summary>
    @JsonProperty("UserAccount")
    private String userAccount;

    /// <summary>
    /// 登录密码
    /// </summary>
    @JsonProperty("UserPassWord")
    private String userPassWord;

    /// <summary>
    /// token
    /// </summary>
    @JsonProperty("Token")
    private String token;

    /// <summary>
    /// appid
    /// </summary>
    @JsonProperty("AppId")
    private String appId;

    /// <summary>
    /// secret
    /// </summary>
    @JsonProperty("Secret")
    private String secret;

    /// <summary>
    /// 商户ID
    /// </summary>
    @JsonProperty("BusinessId")
    private String businessId;

    /// <summary>
    /// 支付密码
    /// </summary>
    @JsonProperty("PayPassWord")
    private String payPassWord;

    /// <summary>
    /// 微信EncodingAESKey
    /// </summary>
    @JsonProperty("EncodingAESKey")
    private String encodingAESKey;

    /// <summary>
    /// 公众号类型
    /// </summary>
    @JsonProperty("AppType")
    private int appType;

    /// <summary>
    /// OpenId获取方式
    /// </summary>
    @JsonProperty("GetOpenIdType")
    private int getOpenIdType;

    /// <summary>
    /// 评房结果通知模板id
    /// </summary>
    @JsonProperty("AssessmentResultTempId")
    private String assessmentResultTempId;

    /// <summary>
    /// 房产评估进度通知模板id
    /// </summary>
    @JsonProperty("AssessmentScheduleTempId")
    private String assessmentScheduleTempId;

    /// <summary>
    /// 询价总次数
    /// </summary>
    @JsonProperty("Count")
    private long count;


    @JsonProperty("LogoImgUrl")
    private String logoImgUrl;


    @JsonProperty("AppName")
    private String appName;


    @JsonProperty("Fields")
    private List<WeChatCustomField> fields;


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

    public String getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getPayPassWord() {
        return payPassWord;
    }

    public void setPayPassWord(String payPassWord) {
        this.payPassWord = payPassWord;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getGetOpenIdType() {
        return getOpenIdType;
    }

    public void setGetOpenIdType(int getOpenIdType) {
        this.getOpenIdType = getOpenIdType;
    }

    public String getAssessmentResultTempId() {
        return assessmentResultTempId;
    }

    public void setAssessmentResultTempId(String assessmentResultTempId) {
        this.assessmentResultTempId = assessmentResultTempId;
    }

    public String getAssessmentScheduleTempId() {
        return assessmentScheduleTempId;
    }

    public void setAssessmentScheduleTempId(String assessmentScheduleTempId) {
        this.assessmentScheduleTempId = assessmentScheduleTempId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<WeChatCustomField> getFields() {
        return fields;
    }

    public void setFields(List<WeChatCustomField> fields) {
        this.fields = fields;
    }
}
