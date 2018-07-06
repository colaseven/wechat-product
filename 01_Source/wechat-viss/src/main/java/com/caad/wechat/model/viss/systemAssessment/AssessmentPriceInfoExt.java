package com.caad.wechat.model.viss.systemAssessment;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class AssessmentPriceInfoExt {
    /// <summary>
    /// 评估id
    /// </summary>
    @JsonProperty("AssessmentId")
    private String assessmentId;

    /// <summary>
    /// 待估物业地址
    /// </summary>
    @JsonProperty("Address")
    private String address;

    /// <summary>
    ///二级物业类型
    /// </summary>
    @JsonProperty("PropertyId")
    private long propertyId;

    /// <summary>
    /// 单据编号
    /// </summary>
    @JsonProperty("Number")
    private String number;

    /// <summary>
    /// 作价状态
    /// </summary>
    @JsonProperty("Status")
    private String status;

    /// <summary>
    /// 消息
    /// </summary>
    @JsonProperty("Message")
    private String message;

    /// <summary>
    /// 建筑面积
    /// </summary>
    @JsonProperty("StructureArea")
    private String structureArea;

    /// <summary>
    /// 房屋+有产权附房列表
    /// </summary>
//    @JsonProperty("HouseItem")
//    private List<PriceItem> houseItem ;

    /// <summary>
    /// 无产权附房列表
    /// </summary>
    @JsonProperty("PropertyFreeItem")
    private List<PriceItem> propertyFreeItem;

    /// <summary>
    ///评估总价(万元)--房屋总价+有产权的附房总价
    /// </summary>
    @JsonProperty("TotalPrice")
    private String totalPrice;

    /// <summary>
    /// 是否为总价
    /// </summary>
    @JsonProperty("IsTotalPrice")
    private String isTotalPrice;

    /// <summary>
    ///评估单价(元)--评估总价÷(房屋面积+有产权附房总面积)*10000
    /// </summary>
    @JsonProperty("UnitPrice")
    private String unitPrice;

    /// <summary>
    /// 装修单价
    /// </summary>
    @JsonProperty("DecorationUnitPrice")
    private String decorationUnitPrice;

    /// <summary>
    /// 装修总价
    /// </summary>
    @JsonProperty("DecorationTotalPrice")
    private String decorationTotalPrice;

    /// <summary>
    ///     无产权的辅房总价
    /// </summary>
    @JsonProperty("PropertyFreePrice")
    private String propertyFreePrice;

    /// <summary>
    /// 是否高于-楼栋基准价
    /// </summary>
    @JsonProperty("UnitBenchmarkPriceAbove")
    private boolean unitBenchmarkPriceAbove;

    /// <summary>
    /// 楼栋基准价
    /// </summary>
    @JsonProperty("UnitBenchmarkPrice")
    private String unitBenchmarkPrice;


    /// <summary>
    /// 是否高于-周边小区均价
    /// </summary>
    @JsonProperty("ProjectAveragePriceAbove")
    private boolean projectAveragePriceAbove;

    /// <summary>
    ///周边小区均价
    /// </summary>
    @JsonProperty("ProjectAveragePrice")
    private String projectAveragePrice;

    /// <summary>
    /// 是否高于-小区基准价
    /// </summary>
    @JsonProperty("ProjectBenchmarkPriceAbove")
    private boolean projectBenchmarkPriceAbove;

    /// <summary>
    /// 小区基准价
    /// </summary>
    @JsonProperty("ProjectBenchmarkPrice")
    private String projectBenchmarkPrice;

    /// <summary>
    /// 基准价来源
    /// </summary>
    @JsonProperty("BasePriceSource")
    private String basePriceSource;

    /// <summary>
    /// 基准价
    /// </summary>
    @JsonProperty("BasicPrice")
    private String basicPrice;

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
    /// 分发id
    /// </summary>
    @JsonProperty("CaseId")
    private String caseId;

    /// <summary>
    /// 是否已付费风险评级
    /// </summary>
    @JsonProperty("IsPaidRisk")
    private boolean isPaidRisk;

    /// <summary>
    /// 剩余次数
    /// </summary>
    @JsonProperty("Surplus")
    private int surplus;

    /// <summary>
    /// 是否有小区风险评级数据
    /// </summary>
    @JsonProperty("IsHaveRiskData")
    private boolean isHaveRiskData;

    /// <summary>
    /// 剩余次数（微信接口使用）
    /// </summary>
    @JsonProperty("ResidueCount")
    private long residueCount;

    /// <summary>
    /// 竣工年份
    /// </summary>
    @JsonProperty("CompletionYear")
    private String completionYear;

    @JsonProperty("UnitName")
    private String unitName;


    @JsonProperty("RoomName")
    private String roomName;


    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    @JsonProperty("FloorId")
    private List<Integer> floorId;


    @JsonProperty("Toward")
    private String toward;


    /// <summary>
    /// 是否显示市场价格区间
    /// </summary>
    @JsonProperty("ShowRang")
    private boolean showRang;

    public boolean isShowRang() {
        return showRang;
    }

    public void setShowRang(boolean showRang) {
        this.showRang = showRang;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStructureArea() {
        return structureArea;
    }

    public void setStructureArea(String structureArea) {
        this.structureArea = structureArea;
    }

    public List<PriceItem> getPropertyFreeItem() {
        return propertyFreeItem;
    }

    public void setPropertyFreeItem(List<PriceItem> propertyFreeItem) {
        this.propertyFreeItem = propertyFreeItem;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDecorationUnitPrice() {
        return decorationUnitPrice;
    }

    public void setDecorationUnitPrice(String decorationUnitPrice) {
        this.decorationUnitPrice = decorationUnitPrice;
    }

    public String getDecorationTotalPrice() {
        return decorationTotalPrice;
    }

    public void setDecorationTotalPrice(String decorationTotalPrice) {
        this.decorationTotalPrice = decorationTotalPrice;
    }

    public String getPropertyFreePrice() {
        return propertyFreePrice;
    }

    public void setPropertyFreePrice(String propertyFreePrice) {
        this.propertyFreePrice = propertyFreePrice;
    }

    public boolean isUnitBenchmarkPriceAbove() {
        return unitBenchmarkPriceAbove;
    }

    public void setUnitBenchmarkPriceAbove(boolean unitBenchmarkPriceAbove) {
        this.unitBenchmarkPriceAbove = unitBenchmarkPriceAbove;
    }

    public String getUnitBenchmarkPrice() {
        return unitBenchmarkPrice;
    }

    public void setUnitBenchmarkPrice(String unitBenchmarkPrice) {
        this.unitBenchmarkPrice = unitBenchmarkPrice;
    }

    public boolean isProjectAveragePriceAbove() {
        return projectAveragePriceAbove;
    }

    public void setProjectAveragePriceAbove(boolean projectAveragePriceAbove) {
        this.projectAveragePriceAbove = projectAveragePriceAbove;
    }

    public String getProjectAveragePrice() {
        return projectAveragePrice;
    }

    public void setProjectAveragePrice(String projectAveragePrice) {
        this.projectAveragePrice = projectAveragePrice;
    }

    public boolean isProjectBenchmarkPriceAbove() {
        return projectBenchmarkPriceAbove;
    }

    public void setProjectBenchmarkPriceAbove(boolean projectBenchmarkPriceAbove) {
        this.projectBenchmarkPriceAbove = projectBenchmarkPriceAbove;
    }

    public String getProjectBenchmarkPrice() {
        return projectBenchmarkPrice;
    }

    public void setProjectBenchmarkPrice(String projectBenchmarkPrice) {
        this.projectBenchmarkPrice = projectBenchmarkPrice;
    }

    public String getBasePriceSource() {
        return basePriceSource;
    }

    public void setBasePriceSource(String basePriceSource) {
        this.basePriceSource = basePriceSource;
    }

    public String getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        this.basicPrice = basicPrice;
    }

    public int getPriceResultType() {
        return priceResultType;
    }

    public void setPriceResultType(int priceResultType) {
        this.priceResultType = priceResultType;
    }

    public void setiSTotalPrice(boolean isTotalPrice) {
        isTotalPrice = isTotalPrice;
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

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public boolean isHaveRiskData() {
        return isHaveRiskData;
    }

    public void setHaveRiskData(boolean haveRiskData) {
        isHaveRiskData = haveRiskData;
    }

    public long getResidueCount() {
        return residueCount;
    }

    public void setResidueCount(long residueCount) {
        this.residueCount = residueCount;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public List<Integer> getFloorId() {
        return floorId;
    }

    public void setFloorId(List<Integer> floorId) {
        this.floorId = floorId;
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIsTotalPrice() {
        return isTotalPrice;
    }

    public void setIsTotalPrice(String isTotalPrice) {
        this.isTotalPrice = isTotalPrice;
    }
}
