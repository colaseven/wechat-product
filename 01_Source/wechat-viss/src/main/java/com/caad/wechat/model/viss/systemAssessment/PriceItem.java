package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class PriceItem {
    /// <summary>
    ///物业类型
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    /// <summary>
    /// 数量+单位
    /// </summary>
    @JsonProperty("Count")
    private String count;
    /// <summary>
    /// 单价
    /// </summary>
    @JsonProperty("UnitPrice")
    private String unitPrice;

    /// <summary>
    /// 总价
    /// </summary>
    @JsonProperty("TotalPrice")
    private String totalPrice;

    /// <summary>
    /// 产权-- 有、无
    /// </summary>
    @JsonProperty("PropertyFree")
    private String propertyFree;

    /// <summary>
    /// 备注
    /// </summary>
    @JsonProperty("Remark")
    private String remark;

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPropertyFree() {
        return propertyFree;
    }

    public void setPropertyFree(String propertyFree) {
        this.propertyFree = propertyFree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
