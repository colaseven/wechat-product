package com.caad.wechat.model.viss.product;

import net.sf.json.JSONObject;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductBoughtModel {

    @JsonProperty("BoughtNumber")
    private String boughtNumber;

    @JsonProperty("Time")
    private String time;

    @JsonProperty("ProductId")
    private String productId;

    @JsonProperty("ProductTypeName")
    private String productTypeName;

    @JsonProperty("Count")
    private String count;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("ModeOfPayment")
    private String modeOfPayment;

    @JsonProperty("UserOpenId")
    private String userOpenId;

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("OrganizationId")
    private long organizationId;

    @JsonProperty("OrganizationName")
    private String organizationName;

    @JsonProperty("CompanyId")
    private String companyId;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("WeChatPayInfo")
    private JSONObject weChatPayInfo;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
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
}

