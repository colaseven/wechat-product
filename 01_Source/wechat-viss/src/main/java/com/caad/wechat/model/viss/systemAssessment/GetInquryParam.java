package com.caad.wechat.model.viss.systemAssessment;


public class GetInquryParam {
    /// <summary>
    /// 二级物业类型
    /// </summary>

    private String propertyId;

    /// <summary>
    /// 小区id
    /// </summary>

    private String projectId;

    /// <summary>
    /// 楼栋Id
    /// </summary>

    private String unitId;

    /// <summary>
    /// 单套id
    /// </summary>

    private String roomId;

    /// <summary>
    /// 手动输入的楼栋名称
    /// </summary>

    private String unitName;

    /// <summary>
    /// 手动输入的户名称
    /// </summary>

    private String roomName;

    /// <summary>
    /// 历史询价Id
    /// </summary>

    private String historyId;

    /// <summary>
    /// 地址
    /// </summary>

    private String address;

    /// <summary>
    /// 四级物业类型(用于精确到小区时切换物业类型,此时需要重新获取修正项)
    /// </summary>

    private String propertyType4;


    private String openId;


    private String userOpenId;


    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType4() {
        return propertyType4;
    }

    public void setPropertyType4(String propertyType4) {
        this.propertyType4 = propertyType4;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }
}
