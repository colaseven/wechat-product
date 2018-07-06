package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InquiryHistoryResultModel {
    /**
     * 询价id
     */
    @JsonProperty("Id")
    private String id;

    /**
     * 二级物业类型
     */
    @JsonProperty("PropertyID")
    private int propertyID;

    /**
     * 二级物业类型名称
     */
    @JsonProperty("PropertyName")
    private String propertyName;

    /**
     * 物业类型
     */
    @JsonProperty("PropertyType")
    private long propertyType;

    /**
     * 物业类型名称
     */
    @JsonProperty("PropertyTypeDisplayName")
    private String propertyTypeDisplayName;

    /**
     * 省
     */
    @JsonProperty("ProvinceCode")
    private long provinceCode;

    @JsonProperty("ProvinceName")
    private String ProvinceName;

    /**
     * 市
     */
    @JsonProperty("RegionCode")
    private long regionCode;

    /**
     * 所在市名
     */
    @JsonProperty("CityName")
    private String cityName;

    /**
     * 区县
     */
    @JsonProperty("CountyCode")
    private long countyCode;


    /**
     * 物业县名
     */
    @JsonProperty("CountyName")
    private String countyName;

    /**
     * /详细地址
     */
    @JsonProperty("DetailAddress")
    private String detailAddress;

    /**
     * 物业地址
     */
    @JsonProperty("ProjectName")
    private String projectName;

    /**
     * 建筑面积
     */
    @JsonProperty("FloorAcreage")
    private String floorAcreage;

    /**
     * 询价时间
     */
    @JsonProperty("CreateTime")
    private String createTime;

    /**
     * 询价时间
     */
    @JsonProperty("CreateTimeStr")
    private String createTimeStr;

    /**
     * 评估总价(包括有产权附房) 只读
     */
    @JsonProperty("AssessTotalPrice")
    private double assessTotalPrice;

    /**
     * 评估单价(包括有产权附房) 只读
     */
    @JsonProperty("AssessSinglePrice")
    private double assessSinglePrice;

    /**
     * 净值
     */
    @JsonProperty("NetWorth")
    private double netWorth;

    /**
     * 询价单号
     */
    @JsonProperty("Number")
    private String number;

    /**
     * CaseId
     */
    @JsonProperty("CaseId")
    private String caseId;

    /**
     * 是否已付费风险评级
     */
    @JsonProperty("IsPaidRisk")
    private boolean isPaidRisk;

    @JsonProperty("EntrustTypeName")
    private String entrustTypeName;

    /**
     * 价值时点
     */
    @JsonProperty("TimePoint")
    private String timePoint;

    /**
     * 询价人名称 只读
     */
    @JsonProperty("InquirerName")
    private String inquirerName;

    /**
     * 估价机构名称-客户对应估价机构名字 只读
     */
    @JsonProperty("InstitutionsName")
    private String institutionsName;

    @JsonProperty("CreateAcount")
    private String createAcount;

    /**
     * 询价机构名称
     */
    @JsonProperty("CompanyName")
    private String companyName;

    /**
     * 估价机构
     */
    @JsonProperty("OrganizeId")
    private long organizeId;

    @JsonProperty("OrganizeName")
    private String organizeName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public long getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(long propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeDisplayName() {
        return propertyTypeDisplayName;
    }

    public void setPropertyTypeDisplayName(String propertyTypeDisplayName) {
        this.propertyTypeDisplayName = propertyTypeDisplayName;
    }

    public long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public long getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(long regionCode) {
        this.regionCode = regionCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(long countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(double assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public double getAssessSinglePrice() {
        return assessSinglePrice;
    }

    public void setAssessSinglePrice(double assessSinglePrice) {
        this.assessSinglePrice = assessSinglePrice;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public boolean isPaidRisk() {
        return isPaidRisk;
    }

    public void setPaidRisk(boolean paidRisk) {
        isPaidRisk = paidRisk;
    }

    public String getEntrustTypeName() {
        return entrustTypeName;
    }

    public void setEntrustTypeName(String entrustTypeName) {
        this.entrustTypeName = entrustTypeName;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    public String getInstitutionsName() {
        return institutionsName;
    }

    public void setInstitutionsName(String institutionsName) {
        this.institutionsName = institutionsName;
    }

    public String getCreateAcount() {
        return createAcount;
    }

    public void setCreateAcount(String createAcount) {
        this.createAcount = createAcount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(long organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}