package com.caad.wechat.model.viss.systemAssessment;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class SystemAssessmentPrice {

    /// <summary>
    /// 待估物业地址
    /// </summary>
    @JsonProperty("Address")
    private String address;

    /// <summary>
    /// 建筑面积
    /// </summary>
    @JsonProperty("StructureArea")
    private String structureArea;

    /// <summary>
    ///评估总价(万元)--房屋总价+有产权的附房总价
    /// </summary>
    @JsonProperty("TotalPrice")
    private String totalPrice;

    /// <summary>
    ///评估单价(元)--评估总价÷(房屋面积+有产权附房总面积)*10000
    /// </summary>
    @JsonProperty("UnitPrice")
    private String unitPrice;

    /// <summary>
    /// 询价结果类型，0未设置，1抵押评估(总价、单价)价格，2合理价格(总价、单价)区间
    /// </summary>
    @JsonProperty("PriceResultType")
    private int priceResultType;

    /// <summary>
    /// 价格上限-单价
    /// </summary>
    @JsonProperty("PriceHighProportion")
    private String priceHighProportion;

    /// <summary>
    /// 价格下限-单价
    /// </summary>
    @JsonProperty("PriceLowProportion")
    private String priceLowProportion;

    /// <summary>
    /// 价格上限-总价
    /// </summary>
    @JsonProperty("TotalPriceHighProportion")
    private String totalPriceHighProportion;

    /// <summary>
    /// 价格下限-总价
    /// </summary>
    @JsonProperty("TotalPriceLowProportion")
    private String totalPriceLowProportion;

    /// <summary>
    /// 剩余次数（微信接口使用）
    /// </summary>
    @JsonProperty("ResidueCount")
    private long residueCount;


    @JsonProperty("TemplateTypes")
    private List<TemplateTypeModel> templateTypes;


    @JsonProperty("Toward")
    private String toward;

    @JsonProperty("CompletionYear")
    private String completionYear;

    @JsonProperty("ShowRang")
    private boolean showRang;

    public boolean isShowRang() {
        return showRang;
    }

    public void setShowRang(boolean showRang) {
        this.showRang = showRang;
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStructureArea() {
        return structureArea;
    }

    public void setStructureArea(String structureArea) {
        this.structureArea = structureArea;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPriceResultType() {
        return priceResultType;
    }

    public void setPriceResultType(int priceResultType) {
        this.priceResultType = priceResultType;
    }

    public String getPriceHighProportion() {
        return priceHighProportion;
    }

    public void setPriceHighProportion(String priceHighProportion) {
        this.priceHighProportion = priceHighProportion;
    }

    public String getPriceLowProportion() {
        return priceLowProportion;
    }

    public void setPriceLowProportion(String priceLowProportion) {
        this.priceLowProportion = priceLowProportion;
    }

    public String getTotalPriceHighProportion() {
        return totalPriceHighProportion;
    }

    public void setTotalPriceHighProportion(String totalPriceHighProportion) {
        this.totalPriceHighProportion = totalPriceHighProportion;
    }

    public String getTotalPriceLowProportion() {
        return totalPriceLowProportion;
    }

    public void setTotalPriceLowProportion(String totalPriceLowProportion) {
        this.totalPriceLowProportion = totalPriceLowProportion;
    }

    public long getResidueCount() {
        return residueCount;
    }

    public void setResidueCount(long residueCount) {
        this.residueCount = residueCount;
    }

    public List<TemplateTypeModel> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(List<TemplateTypeModel> templateTypes) {
        this.templateTypes = templateTypes;
    }
}
