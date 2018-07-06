package com.caad.wechat.model.viss.assessAgent;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportListDto {
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
    private long province;

    @JsonProperty("ProvinceName")
    private String provinceName;

    /// <summary>
    /// 物业所在市
    /// </summary>
    @JsonProperty("City")
    private long city;

    @JsonProperty("CityName")
    private String cityName;

    /// <summary>
    /// 物业所在县
    /// </summary>
    @JsonProperty("County")
    private long county;

    @JsonProperty("CountyName")
    private String countyName;

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
    /// 详细地址
    /// </summary>
    @JsonProperty("DetailAddress")
    private String detailAddress;

    /// <summary>
    /// 创建时间
    /// </summary>
    @JsonProperty("CreateTime")
    private String createTime;

    /// <summary>
    /// 没有交费的次数
    /// null表示没有发起交费
    /// </summary>
    @JsonProperty("NoChargeCount")
    private String noChargeCount;

    /// <summary>
    /// 单价
    /// </summary>
    @JsonProperty("AssessSinglePrice")
    private String assessSinglePrice;

    /// <summary>
    /// 总价
    /// </summary>
    @JsonProperty("AssessTotalPrice")
    private String assessTotalPrice;

    /// <summary>
    /// 状态
    /// </summary>
    @JsonProperty("State")
    private String state;

    /// <summary>
    /// 业务状态
    /// </summary>
    @JsonProperty("ClassName")
    private String className;


    /// <summary>
    /// 物业类型
    /// </summary>
    @JsonProperty("PropertyType")
    private String propertyType;

    /// <summary>
    /// 物业类型
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;


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

    public long getProvince() {
        return province;
    }

    public void setProvince(long province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCounty() {
        return county;
    }

    public void setCounty(long county) {
        this.county = county;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNoChargeCount() {
        return noChargeCount;
    }

    public void setNoChargeCount(String noChargeCount) {
        this.noChargeCount = noChargeCount;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }
}
