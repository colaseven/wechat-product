package com.caad.wechat.model.viss.aritificial;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatEntrustInformationParam {


    private String id;

    private String province;

    private String city;

    private String county;

    private String propertyType;

    private String proposer;

    private String detailAddress;

    private String productId;

    private String productName;

    private String projectName;

    private String floorAcreage;

    private String timePoint;

    private String remarks;

    private String userOpenId;

    private String openId;

    private List<ImgInfo> imgs;

    private String purchaseTime;

    private String purchasePrice;

    private String internalArea;

    private String completionYear;

    private String landTenureTypes;

    private String landTenureTypeName;

    private String purpose;

    private String purposeName;

    private String obligee;

    private String obligeeName;

    private String inquirerName;//询价人姓名

    private String mobilePhone;//询价人电话

    /**
     * 微信昵称
     */
    private String weChatNickName;

    /**
     * 微信公众号名称
     */
    private String weChatAppName;

    /**
     * 产品类型
     */
    private String entrustType;

    /**
     * 委托联系人
     */
    private String entrustLinkman;

    /**
     * 委托联系人电话
     */
    private String entrustMobilePhone;

    /**
     * 报告快递地址
     */
    private String reportexpressAddress;

    /**
     * 产权人
     */
    private String obligeeUserName;

    /**
     * 土地面积
     */
    private String landAcreage;

    /**
     * 估价目的
     */
    private String appraisalObjective;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFloorAcreage() {
        return floorAcreage;
    }

    public void setFloorAcreage(String floorAcreage) {
        this.floorAcreage = floorAcreage;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public List<ImgInfo> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgInfo> imgs) {
        this.imgs = imgs;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getInternalArea() {
        return internalArea;
    }

    public void setInternalArea(String internalArea) {
        this.internalArea = internalArea;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getLandTenureTypes() {
        return landTenureTypes;
    }

    public void setLandTenureTypes(String landTenureTypes) {
        this.landTenureTypes = landTenureTypes;
    }

    public String getLandTenureTypeName() {
        return landTenureTypeName;
    }

    public void setLandTenureTypeName(String landTenureTypeName) {
        this.landTenureTypeName = landTenureTypeName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getObligee() {
        return obligee;
    }

    public void setObligee(String obligee) {
        this.obligee = obligee;
    }

    public String getObligeeName() {
        return obligeeName;
    }

    public void setObligeeName(String obligeeName) {
        this.obligeeName = obligeeName;
    }

    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWeChatNickName() {
        return weChatNickName;
    }

    public void setWeChatNickName(String weChatNickName) {
        this.weChatNickName = weChatNickName;
    }

    public String getWeChatAppName() {
        return weChatAppName;
    }

    public void setWeChatAppName(String weChatAppName) {
        this.weChatAppName = weChatAppName;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public String getEntrustLinkman() {
        return entrustLinkman;
    }

    public void setEntrustLinkman(String entrustLinkman) {
        this.entrustLinkman = entrustLinkman;
    }

    public String getEntrustMobilePhone() {
        return entrustMobilePhone;
    }

    public void setEntrustMobilePhone(String entrustMobilePhone) {
        this.entrustMobilePhone = entrustMobilePhone;
    }

    public String getReportexpressAddress() {
        return reportexpressAddress;
    }

    public void setReportexpressAddress(String reportexpressAddress) {
        this.reportexpressAddress = reportexpressAddress;
    }

    public String getObligeeUserName() {
        return obligeeUserName;
    }

    public void setObligeeUserName(String obligeeUserName) {
        this.obligeeUserName = obligeeUserName;
    }

    public String getLandAcreage() {
        return landAcreage;
    }

    public void setLandAcreage(String landAcreage) {
        this.landAcreage = landAcreage;
    }

    public String getAppraisalObjective() {
        return appraisalObjective;
    }

    public void setAppraisalObjective(String appraisalObjective) {
        this.appraisalObjective = appraisalObjective;
    }
}
