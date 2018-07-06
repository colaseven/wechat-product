package com.caad.wechat.model.viss.aritificial;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatEntrustinfomationResult {


    @JsonProperty("Id")
    private String id;

    @JsonProperty("Number")
    private String number;

    @JsonProperty("Province")
    private String province;

    @JsonProperty("ProvinceName")
    private String provinceName;

    @JsonProperty("City")
    private String city;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("County")
    private String county;

    @JsonProperty("CountyName")
    private String countyName;

    @JsonProperty("PropertyType")
    private String propertyType;

    @JsonProperty("Proposer")
    private String proposer;

    @JsonProperty("DetailAddress")
    private String detailAddress;

    @JsonProperty("ProductId")
    private String productId;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("ProjectName")
    private String projectName;

    @JsonProperty("FloorAcreage")
    private double floorAcreage;

    @JsonProperty("TimePoint")
    private String timePoint;

    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("CreateTime")
    private String createTime;

    @JsonProperty("FinishTime")
    private String finishTime;

    @JsonProperty("PropertyTypeDisplayName")
    private String propertyTypeDisplayName;

    @JsonProperty("ReadOnlyState")
    private int readOnlyState;

    @JsonProperty("ClassName")
    private String className;

    @JsonProperty("AssessSinglePrice")
    private double assessSinglePrice;

    @JsonProperty("AssessTotalPrice")
    private double assessTotalPrice;

    @JsonProperty("UserOpenId")
    private String userOpenId;

    @JsonProperty("OpenId")
    private String openId;

    @JsonProperty("PurchaseTime")
    private String purchaseTime;

    @JsonProperty("PurchasePrice")
    private double purchasePrice;

    @JsonProperty("InternalArea")
    private double internalArea;

    @JsonProperty("CompletionYear")
    private String completionYear;

    @JsonProperty("LandTenureTypes")
    private long landTenureTypes;

    @JsonProperty("LandTenureTypeName")
    private String landTenureTypeName;

    @JsonProperty("Purpose")
    private long purpose;

    @JsonProperty("PurposeName")
    private String purposeName;

    @JsonProperty("Obligee")
    private long obligee;

    @JsonProperty("ObligeeName")
    private String obligeeName;

    @JsonProperty("InquirerName")
    private String inquirerName;//询价人姓名

    @JsonProperty("MobilePhone")
    private String mobilePhone;//询价人电话

    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public double getFloorAcreage() {
        return floorAcreage;
    }

    public void setFloorAcreage(double floorAcreage) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPropertyTypeDisplayName() {
        return propertyTypeDisplayName;
    }

    public void setPropertyTypeDisplayName(String propertyTypeDisplayName) {
        this.propertyTypeDisplayName = propertyTypeDisplayName;
    }

    public int getReadOnlyState() {
        return readOnlyState;
    }

    public void setReadOnlyState(int readOnlyState) {
        this.readOnlyState = readOnlyState;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getAssessSinglePrice() {
        return assessSinglePrice;
    }

    public void setAssessSinglePrice(double assessSinglePrice) {
        this.assessSinglePrice = assessSinglePrice;
    }

    public double getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(double assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
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

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getInternalArea() {
        return internalArea;
    }

    public void setInternalArea(double internalArea) {
        this.internalArea = internalArea;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public long getLandTenureTypes() {
        return landTenureTypes;
    }

    public void setLandTenureTypes(long landTenureTypes) {
        this.landTenureTypes = landTenureTypes;
    }

    public String getLandTenureTypeName() {
        return landTenureTypeName;
    }

    public void setLandTenureTypeName(String landTenureTypeName) {
        this.landTenureTypeName = landTenureTypeName;
    }

    public long getPurpose() {
        return purpose;
    }

    public void setPurpose(long purpose) {
        this.purpose = purpose;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public long getObligee() {
        return obligee;
    }

    public void setObligee(long obligee) {
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
}
