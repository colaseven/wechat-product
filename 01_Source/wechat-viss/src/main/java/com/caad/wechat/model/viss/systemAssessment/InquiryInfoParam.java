package com.caad.wechat.model.viss.systemAssessment;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class InquiryInfoParam {


    @JsonProperty("Id")
    private String id;

    @JsonProperty("PropertyType")
    private String propertyType;

    @JsonProperty("PropertyId")
    private String propertyId;

    @JsonProperty("PropertyTypeTwo")
    private String propertyTypeTwo;

    @JsonProperty("PropertyTypeThree")
    private String propertyTypeThree;

    @JsonProperty("PropertyTypeFour")
    private String propertyTypeFour;

    @JsonProperty("ProvinceCode")
    private String provinceCode;

    @JsonProperty("CityCode")
    private String cityCode;

    @JsonProperty("CountyCode")
    private String countyCode;

    @JsonProperty("CountyCodeText")
    private String countyCodeText;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("CommunityCode")
    private String communityCode;

    @JsonProperty("BuildingCode")
    private String buildingCode;

    @JsonProperty("RoomCode")
    private String roomCode;

    @JsonProperty("FloorUnit")
    private String floorUnit;

    @JsonProperty("RoomUnit")
    private String roomUnit;

    @JsonProperty("BuildingNumber")
    private String buildingNumber;

    @JsonProperty("BuildingName")
    private String buildingName;

    @JsonProperty("HouseholdsNumber")
    private String householdsNumber;

    @JsonProperty("ProjectId")
    private String projectId;

    @JsonProperty("RoomId")
    private String roomId;

    @JsonProperty("MatchNum")
    private String matchNum;

    @JsonProperty("Level")
    private String level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyTypeTwo() {
        return propertyTypeTwo;
    }

    public void setPropertyTypeTwo(String propertyTypeTwo) {
        this.propertyTypeTwo = propertyTypeTwo;
    }

    public String getPropertyTypeThree() {
        return propertyTypeThree;
    }

    public void setPropertyTypeThree(String propertyTypeThree) {
        this.propertyTypeThree = propertyTypeThree;
    }

    public String getPropertyTypeFour() {
        return propertyTypeFour;
    }

    public void setPropertyTypeFour(String propertyTypeFour) {
        this.propertyTypeFour = propertyTypeFour;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyCodeText() {
        return countyCodeText;
    }

    public void setCountyCodeText(String countyCodeText) {
        this.countyCodeText = countyCodeText;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getFloorUnit() {
        return floorUnit;
    }

    public void setFloorUnit(String floorUnit) {
        this.floorUnit = floorUnit;
    }

    public String getRoomUnit() {
        return roomUnit;
    }

    public void setRoomUnit(String roomUnit) {
        this.roomUnit = roomUnit;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getHouseholdsNumber() {
        return householdsNumber;
    }

    public void setHouseholdsNumber(String householdsNumber) {
        this.householdsNumber = householdsNumber;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(String matchNum) {
        this.matchNum = matchNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
