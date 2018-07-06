package com.caad.wechat.model.viss.systemAssessment;


import com.caad.wechat.model.viss.common.SystemResultTabShow;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class SystemAssessmentResult {


    /// <summary>
    ///     默认室名
    /// </summary>
    @JsonProperty("RoomName")
    private String RoomName;

    /// <summary>
    /// 楼栋名
    /// </summary>
    @JsonProperty("UnitName")
    private String unitName;

    /// <summary>
    ///
    /// </summary>
    @JsonProperty("Address")
    private String address;

    /// <summary>
    /// 所在楼层 区间，2个值 起始楼层 ，结束楼层
    /// </summary>
    @JsonProperty("Floor")
    private List<Integer> floor;

    /// <summary>
    /// 物业类型名字 评估的时候携带
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    /// <summary>
    /// 界面展示模板
    /// </summary>
    @JsonProperty("Codens")
    private List<UiTemplate> codens;

    /// <summary>
    /// 价格详情
    /// </summary>
    @JsonProperty("HistoryPrice")
    private SystemAssessmentPrice historyPrice;


    @JsonProperty("CommunityId")
    private String communityId;


    @JsonProperty("UserCollocation")
    private String userCollocation;


    @JsonProperty("WeChatResultDto")
    private SystemResultTabShow weChatResultDto;

    @JsonProperty("ProjectName")
    private String projectName;

    @JsonProperty("PropertyId")
    private String propertyId;


    @JsonProperty("ProvinceCode")
    private String provinceCode;


    @JsonProperty("RegionCode")
    private String regionCode;

    @JsonProperty("CountyCode")
    private String countyCode;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUserCollocation() {
        return userCollocation;
    }

    public void setUserCollocation(String userCollocation) {
        this.userCollocation = userCollocation;
    }

    public SystemResultTabShow getWeChatResultDto() {
        return weChatResultDto;
    }

    public void setWeChatResultDto(SystemResultTabShow weChatResultDto) {
        this.weChatResultDto = weChatResultDto;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Integer> getFloor() {
        return floor;
    }

    public void setFloor(List<Integer> floor) {
        this.floor = floor;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public List<UiTemplate> getCodens() {
        return codens;
    }

    public void setCodens(List<UiTemplate> codens) {
        this.codens = codens;
    }

    public SystemAssessmentPrice getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(SystemAssessmentPrice historyPrice) {
        this.historyPrice = historyPrice;
    }
}
