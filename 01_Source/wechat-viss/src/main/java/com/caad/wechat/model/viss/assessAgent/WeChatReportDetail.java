package com.caad.wechat.model.viss.assessAgent;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatReportDetail {
    /// <summary>
    /// 单据id
    /// </summary>
    @JsonProperty("Id")
    private String id;

    /// <summary>
    /// 单据编号
    /// </summary>
    @JsonProperty("Number")
    private String number;

    /// <summary>
    /// 物业省份
    /// </summary>
    @JsonProperty("Province")
    private String province;

    /// <summary>
    /// 物业省份名
    /// </summary>
    @JsonProperty("ProvinceName")
    private String provinceName;

    /// <summary>
    /// 物业所在市
    /// </summary>
    @JsonProperty("City")
    private String city;

    /// <summary>
    /// 物业所在市名
    /// </summary>
    @JsonProperty("CityName")
    private String cityName;

    /// <summary>
    /// 物业所在县
    /// </summary>
    @JsonProperty("County")
    private String county;

    /// <summary>
    /// 物业所在县名
    /// </summary>
    @JsonProperty("CountyName")
    private String countyName;

    /// <summary>
    /// 物业类型
    /// </summary>
    @JsonProperty("PropertyType")
    private String propertyType;

    /// <summary>
    /// 贷款申请人
    /// </summary>
    @JsonProperty("Proposer")
    private String proposer;

    /// <summary>
    /// 详细地址
    /// </summary>
    @JsonProperty("DetailAddress")
    private String detailAddress;

    /// <summary>
    /// 产品id
    /// </summary>
    @JsonProperty("ProductId")
    private String productId;

    /// <summary>
    /// 产品名称
    /// </summary>
    @JsonProperty("ProductName")
    private String productName;

    /// <summary>
    /// 所属项目
    /// </summary>
    @JsonProperty("ProjectName")
    private String projectName;

    /// <summary>
    /// 建筑面积
    /// </summary>
    @JsonProperty("FloorAcreage")
    private String floorAcreage;

    /// <summary>
    /// 价值时点
    /// </summary>
    @JsonProperty("TimePoint")

    private String timePoint;

    /// <summary>
    /// 备注
    /// </summary>
    @JsonProperty("Remarks")
    private String remarks;

    /// <summary>
    /// 创建时间
    /// </summary>
    @JsonProperty("CreateTime")
    private String createTime;

    /// <summary>
    /// 完成时间
    /// </summary>
    @JsonProperty("FinishTime")
    private String finishTime;

    /// <summary>
    /// 物业类型名
    /// </summary>
    @JsonProperty("PropertyTypeDisplayName")
    private String propertyTypeDisplayName;

    /// <summary>
    /// 业务状态
    /// </summary>
    @JsonProperty("ReadOnlyState")
    private int readOnlyState;

    /// <summary>
    /// 业务状态
    /// </summary>
    @JsonProperty("ClassName")
    private String className;

    /// <summary>
    /// 评估单价(元)
    /// </summary>
    @JsonProperty("AssessSinglePrice")
    private String assessSinglePrice;

    /// <summary>
    /// 评估总价(万元)
    /// </summary>
    @JsonProperty("AssessTotalPrice")
    private String assessTotalPrice;


    /// <summary>
    /// 用户openid
    /// </summary>
    @JsonProperty("TimePoint")
    private String UserOpenId;

    /// <summary>
    /// 公众号id
    /// </summary>
    @JsonProperty("OpenId")
    private String openId;

    /// <summary>
    /// 购房日期
    /// </summary>
    @JsonProperty("PurchaseTime")
    private String purchaseTime;


    /// <summary>
    /// 登记价格
    /// </summary>
    @JsonProperty("PurchasePrice")
    private String purchasePrice;

    /// <summary>
    /// 套内面积
    /// </summary>
    @JsonProperty("InternalArea")
    private String internalArea;

    /// <summary>
    /// 建筑年份
    /// </summary>
    @JsonProperty("CompletionYear")
    private String completionYear;

    /// <summary>
    /// 使用权来源
    /// </summary>
    @JsonProperty("LandTenureTypes")
    private String landTenureTypes;

    /// <summary>
    /// 使用权来源
    /// </summary>
    @JsonProperty("LandTenureTypeName")
    private String landTenureTypeName;

    /// <summary>
    /// 证载用途
    /// </summary>
    @JsonProperty("Purpose")
    private String purpose;

    /// <summary>
    /// 证载用途
    /// </summary>
    @JsonProperty("PurposeName")
    private String purposeName;

    /// <summary>
    /// 权益人属性
    /// </summary>
    @JsonProperty("Obligee")
    private String obligee;

    /// <summary>
    /// 权益人属性
    /// </summary>
    @JsonProperty("ObligeeName")
    private String obligeeName;

    /// <summary>
    /// 询价人姓名
    /// </summary>
    @JsonProperty("InquirerName")
    private String inquirerName;

    /// <summary>
    /// 询价人电话
    /// </summary>
    @JsonProperty("MobilePhone")
    private String mobilePhone;

    /// <summary>
    /// 报告地址
    /// </summary>
    @JsonProperty("ReportUrl")
    private String reportUrl;


    //委托人姓名
    @JsonProperty("EntrustLinkman")
    private String entrustLinkman;

    //委托人电话
    @JsonProperty("EntrustMobilePhone")
    private String entrustMobilePhone;

    //报告快递地址
    @JsonProperty("ReportexpressAddress")
    private String reportexpressAddress;

    //产权人
    @JsonProperty("ObligeeUserName")
    private String obligeeUserName;

    //土地面积
    @JsonProperty("LandAcreage")
    private String landAcreage;

    //估价目的
    @JsonProperty("AppraisalObjective")
    private String appraisalObjective;

    /// <summary>
    /// 没有交费的次数
    /// null表示没有发起交费
    /// </summary>
    @JsonProperty("NoChargeCount")
    private String noChargeCount;

    /// <summary>
    /// 待支付
    /// </summary>
    @JsonProperty("ToBePaid")
    private String toBePaid;

    /// <summary>
    /// 已支付
    /// </summary>
    @JsonProperty("AlreadyPaid")
    private String alreadyPaid;

    /// <summary>
    /// 估价目的
    /// </summary>
    @JsonProperty("AppraisalObjectiveName")
    private String appraisalObjectiveName;


    @JsonProperty("ToBePaidIdList")
    private String toBePaidIdList;


    private String telephone;


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

    public String getNoChargeCount() {
        return noChargeCount;
    }

    public void setNoChargeCount(String noChargeCount) {
        this.noChargeCount = noChargeCount;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public String getAssessSinglePrice() {
        return assessSinglePrice;
    }

    public void setAssessSinglePrice(String assessSinglePrice) {
        this.assessSinglePrice = assessSinglePrice;
    }

    public String getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(String assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public String getUserOpenId() {
        return UserOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        UserOpenId = userOpenId;
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

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(String toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getAlreadyPaid() {
        return alreadyPaid;
    }

    public void setAlreadyPaid(String alreadyPaid) {
        this.alreadyPaid = alreadyPaid;
    }

    public String getAppraisalObjectiveName() {
        return appraisalObjectiveName;
    }

    public void setAppraisalObjectiveName(String appraisalObjectiveName) {
        this.appraisalObjectiveName = appraisalObjectiveName;
    }

    public String getToBePaidIdList() {
        return toBePaidIdList;
    }

    public void setToBePaidIdList(String toBePaidIdList) {
        this.toBePaidIdList = toBePaidIdList;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
