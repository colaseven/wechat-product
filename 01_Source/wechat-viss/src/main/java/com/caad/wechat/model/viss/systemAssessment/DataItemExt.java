package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class DataItemExt {
    /// <summary>
    /// 名称
    /// </summary>
    @JsonProperty("Name")
    private String name;

    /// <summary>
    /// 值
    /// </summary>
    @JsonProperty("Value")
    private String value;

    /// <summary>
    /// 4级物业类型
    /// </summary>
    @JsonProperty("PropertyType4")
    private String propertyType4;

    /// <summary>
    /// 三级物业
    /// </summary>
    @JsonProperty("PropertyType3")
    private String propertyType3;

    /// <summary>
    /// 描述
    /// </summary>
    @JsonProperty("Description")
    private String description;

    /// <summary>
    /// 楼栋号
    /// </summary>
    @JsonProperty("FloorNumber")
    private String floorNumber;

    /// <summary>
    /// 楼栋单位Id
    /// </summary>
    @JsonProperty("FloorUnitId")
    private String floorUnitId;

    /// <summary>
    /// 楼栋单位
    /// </summary>
    @JsonProperty("FloorUnit")
    private String floorUnit;

    /// <summary>
    /// 室单位Id
    /// </summary>
    @JsonProperty("RoomUnitId")
    private String roomUnitId;

    /// <summary>
    /// 室单位
    /// </summary>
    @JsonProperty("RoomUnit")
    private String roomUnit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPropertyType4() {
        return propertyType4;
    }

    public void setPropertyType4(String propertyType4) {
        this.propertyType4 = propertyType4;
    }

    public String getPropertyType3() {
        return propertyType3;
    }

    public void setPropertyType3(String propertyType3) {
        this.propertyType3 = propertyType3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFloorUnitId() {
        return floorUnitId;
    }

    public void setFloorUnitId(String floorUnitId) {
        this.floorUnitId = floorUnitId;
    }

    public String getFloorUnit() {
        return floorUnit;
    }

    public void setFloorUnit(String floorUnit) {
        this.floorUnit = floorUnit;
    }

    public String getRoomUnitId() {
        return roomUnitId;
    }

    public void setRoomUnitId(String roomUnitId) {
        this.roomUnitId = roomUnitId;
    }

    public String getRoomUnit() {
        return roomUnit;
    }

    public void setRoomUnit(String roomUnit) {
        this.roomUnit = roomUnit;
    }
}
