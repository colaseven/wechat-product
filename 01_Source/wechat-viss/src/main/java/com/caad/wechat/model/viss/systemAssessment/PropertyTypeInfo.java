package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class PropertyTypeInfo {
    /// <summary>
    /// 四级物业类型
    /// </summary>
    @JsonProperty("PropertyTypeId")
    private long propertyTypeId;

    /// <summary>
    /// 编号
    /// </summary>
    @JsonProperty("propertyTypeCode")
    private String propertyTypeCode;

    /// <summary>
    /// 名称
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    /// <summary>
    /// 计价单位
    /// </summary>
    @JsonProperty("ValuationUnit")
    private String valuationUnit;

    /// <summary>
    /// 是否有产权
    /// </summary>
    @JsonProperty("Equity")
    private String equity;

    /// <summary>
    /// 描述
    /// </summary>
    @JsonProperty("Description")
    private String description;

    /// <summary>
    /// 计价方式-独立计价
    /// </summary>
    @JsonProperty("Independentvaluation")
    private String independentvaluation;

    /// <summary>
    /// 基准楼层
    /// </summary>
    @JsonProperty("BaseFloor")
    private String baseFloor;

    public long getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getValuationUnit() {
        return valuationUnit;
    }

    public void setValuationUnit(String valuationUnit) {
        this.valuationUnit = valuationUnit;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndependentvaluation() {
        return independentvaluation;
    }

    public void setIndependentvaluation(String independentvaluation) {
        this.independentvaluation = independentvaluation;
    }

    public String getBaseFloor() {
        return baseFloor;
    }

    public void setBaseFloor(String baseFloor) {
        this.baseFloor = baseFloor;
    }
}
