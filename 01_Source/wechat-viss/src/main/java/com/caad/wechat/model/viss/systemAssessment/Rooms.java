package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class Rooms {
    @JsonProperty("RoomInfos")
    private List<RoomInfo> roomInfos;

    /// <summary>
    ///     总楼层数
    /// </summary>
    @JsonProperty("TotalFloor")
    private int totalFloor;

    /// <summary>
    ///     总单元数
    /// </summary>
    @JsonProperty("TotalUnit")
    private int totalUnit;

    public List<RoomInfo> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(List<RoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public int getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(int totalUnit) {
        this.totalUnit = totalUnit;
    }
}
