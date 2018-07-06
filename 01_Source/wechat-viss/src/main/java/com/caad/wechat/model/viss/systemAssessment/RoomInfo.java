package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class RoomInfo {
    /// <summary>
    ///     起始层
    /// </summary>
    @JsonProperty("Layer1")
    private String layer1;

    /// <summary>
    ///     结束层
    /// </summary>
    @JsonProperty("Layer2")
    private String layer2;

    /// <summary>
    ///     单元
    /// </summary>
    @JsonProperty("UnitNumber")
    private String unitNumber;

    /// <summary>
    ///     室名
    /// </summary>
    @JsonProperty("RoomNumber")
    private String roomNumber;

    /// <summary>
    ///     四级物业类型
    /// </summary>
    @JsonProperty("PropertyType4")
    private String propertyType4;

    /// <summary>
    ///     三级物业
    /// </summary>
    @JsonProperty("PropertyType3")
    private String propertyType3;

    /// <summary>
    ///     室id
    /// </summary>
    @JsonProperty("RoomId")
    private String roomId;


    private String roomName;//拼接后的室号

    public String getLayer1() {
        return layer1;
    }

    public void setLayer1(String layer1) {
        this.layer1 = layer1;
    }

    public String getLayer2() {
        return layer2;
    }

    public void setLayer2(String layer2) {
        this.layer2 = layer2;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
