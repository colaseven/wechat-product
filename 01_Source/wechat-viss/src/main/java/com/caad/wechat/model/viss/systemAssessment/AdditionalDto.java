package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class AdditionalDto {

    @JsonProperty("Id")
    private long id;

    /// <summary>
    /// 系统询价Id
    /// </summary>
    @JsonProperty("AssessmentId")
    private long assessmentId;

    /// <summary>
    /// 物业选项
    /// </summary>
    @JsonProperty("AdditionalTerm")
    private String additionalTerm;

    /// <summary>
    /// 物业类型名称
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    /// <summary>
    /// 面积
    /// </summary>
    @JsonProperty("AdditionalArea")
    private String additionalArea;

    /// <summary>
    /// 面积单位
    /// </summary>
    @JsonProperty("AreaUnit")
    private String areaUnit;

    /// <summary>
    /// 评估单价
    /// </summary>
    @JsonProperty("UnitPrice")
    private double unitPrice;

    /// <summary>
    /// 评估总价
    /// </summary>
    @JsonProperty("TotalPrice")
    private String totalPrice;

    /// <summary>
    /// 产权
    /// </summary>
    @JsonProperty("HavingProperty")
    private boolean havingProperty;

    /// <summary>
    /// 占位符
    /// </summary>
    @JsonProperty("Netspace")
    private String netspace;

    /// <summary>
    /// 备注
    /// </summary>
    @JsonProperty("Remark")
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAdditionalTerm() {
        return additionalTerm;
    }

    public void setAdditionalTerm(String additionalTerm) {
        this.additionalTerm = additionalTerm;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getAdditionalArea() {
        return additionalArea;
    }

    public void setAdditionalArea(String additionalArea) {
        this.additionalArea = additionalArea;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isHavingProperty() {
        return havingProperty;
    }

    public void setHavingProperty(boolean havingProperty) {
        this.havingProperty = havingProperty;
    }

    public String getNetspace() {
        return netspace;
    }

    public void setNetspace(String netspace) {
        this.netspace = netspace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
