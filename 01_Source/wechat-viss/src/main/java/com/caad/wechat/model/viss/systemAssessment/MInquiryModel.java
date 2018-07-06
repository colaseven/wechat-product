package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class MInquiryModel {


    @JsonProperty("SessionGuid")
    private String sessionGuid;


    /// <summary>
    ///     楼栋列表
    /// </summary>
    @JsonProperty("UnitId")
    private List<DataItemExt> unitId;

    /// <summary>
    ///     二级物业Id
    /// </summary>
    @JsonProperty("PropertyId")
    private long propertyId;

    /// <summary>
    /// 四级物业
    /// </summary>
    @JsonProperty("PropertyTypeCode")
    private String propertyTypeCode;

    /// <summary>
    ///     评估参数-地区
    /// </summary>
    @JsonProperty("AreaID")
    private long areaID;


    /// <summary>
    ///     评估参数-SettingType=107
    /// </summary>
    @JsonProperty("SettingType")
    private int settingType;

    /// <summary>
    ///     获取修正系数时间
    /// </summary>
    @JsonProperty("MakePriceTime")
    private String makePriceTime;

    /// <summary>
    ///     评估参数-评估公式
    /// </summary>
    @JsonProperty("MethodID")
    private int methodID;

    @JsonProperty("Address")
    private String address;

    /// <summary>
    ///     小区Id
    /// </summary>
    @JsonProperty("CommunityId")
    private String communityId;

    /// <summary>
    ///     小区名称
    /// </summary>
    @JsonProperty("ProjectName")
    private String projectName;

    /// <summary>
    ///     默认楼栋id
    /// </summary>
    @JsonProperty("UnitNo")
    private String unitNo;

    /// <summary>
    ///     楼栋名
    /// </summary>
    @JsonProperty("UnitName")
    private String unitName;

    /// <summary>
    ///     所有室号
    /// </summary>
    @JsonProperty("RoomNo")
    private Rooms roomNo;

    /// <summary>
    ///     默认室Id
    /// </summary>
    @JsonProperty("RoomId")
    private String roomId;

    /// <summary>
    ///     默认室名
    /// </summary>
    @JsonProperty("RoomName")
    private String roomName;

    /// <summary>
    ///     物业类型
    /// </summary>
    @JsonProperty("PropertyDataSource")
    private List<DataItem> propertyDataSource;

    /// <summary>
    ///     所在楼层 区间，2个值 起始楼层 ，结束楼层
    /// </summary>
    @JsonProperty("Floor")
    private List<Integer> floor;

    /// <summary>
    ///     总层 区间，2个值起始楼层 ，结束楼层
    /// </summary>
    @JsonProperty("TotalFloor")
    private List<Integer> totalFloor;
    /// <summary>
    ///     物业类型名字 评估的时候携带
    /// </summary>
    @JsonProperty("PropertyTypeName")
    private String propertyTypeName;

    /// <summary>
    ///     物业类型Id  评估的时候携带
    /// </summary>
    @JsonProperty("PropertyTypeId")
    private String propertyTypeId;

    /// <summary>
    ///     界面展示模板
    /// </summary>
    @JsonProperty("Codens")
    private List<UiTemplate> codens;

    /// <summary>
    ///     附加物业数据界面展现数据
    /// </summary>
    @JsonProperty("PropertyTypeInfos")
    private List<PropertyTypeInfo> propertyTypeInfos;

    /// <summary>
    ///     默认附加物业业务数据
    /// </summary>
    @JsonProperty("Additionals")
    private List<AdditionalDto> additionals;

    /// <summary>
    ///     历史价格（价格详情）
    /// </summary>
    @JsonProperty("HistoryPrice")
    private AssessmentPriceExt historyPrice;

    /// <summary>
    ///     是否是历史记录
    /// </summary>
    @JsonProperty("IsHistory")
    private boolean isHistory;

    /// <summary>
    ///     省
    /// </summary>
    @JsonProperty("ProvinceCode")
    private String provinceCode;

    /// <summary>
    ///     市
    /// </summary>
    @JsonProperty("RegionCode")
    private String regionCode;

    /// <summary>
    ///     区县
    /// </summary>
    @JsonProperty("CountyCode")
    private String countyCode;

    /// <summary>
    ///     GIS
    /// </summary>
    @JsonProperty("GisImg")
    private GisImgInfo gisImg;

    /// <summary>
    ///     小区默认图
    /// </summary>
    @JsonProperty("ImgPath")
    private String imgPath;

    /// <summary>
    ///     是否出具过电子询价单
    /// </summary>
    @JsonProperty("IsCompleted")
    private boolean isCompleted;

    /// <summary>
    /// 是否学区房
    /// </summary>
    @JsonProperty("IsSchoolDistrict")
    private String isSchoolDistrict;


    public String getSessionGuid() {
        return sessionGuid;
    }

    public void setSessionGuid(String sessionGuid) {
        this.sessionGuid = sessionGuid;
    }

    public List<DataItemExt> getUnitId() {
        return unitId;
    }

    public void setUnitId(List<DataItemExt> unitId) {
        this.unitId = unitId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

    public long getAreaID() {
        return areaID;
    }

    public void setAreaID(long areaID) {
        this.areaID = areaID;
    }

    public int getSettingType() {
        return settingType;
    }

    public void setSettingType(int settingType) {
        this.settingType = settingType;
    }

    public String getMakePriceTime() {
        return makePriceTime;
    }

    public void setMakePriceTime(String makePriceTime) {
        this.makePriceTime = makePriceTime;
    }

    public int getMethodID() {
        return methodID;
    }

    public void setMethodID(int methodID) {
        this.methodID = methodID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Rooms getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Rooms roomNo) {
        this.roomNo = roomNo;
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

    public List<DataItem> getPropertyDataSource() {
        return propertyDataSource;
    }

    public void setPropertyDataSource(List<DataItem> propertyDataSource) {
        this.propertyDataSource = propertyDataSource;
    }

    public List<Integer> getFloor() {
        return floor;
    }

    public void setFloor(List<Integer> floor) {
        this.floor = floor;
    }

    public List<Integer> getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(List<Integer> totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public List<UiTemplate> getCodens() {
        return codens;
    }

    public void setCodens(List<UiTemplate> codens) {
        this.codens = codens;
    }

    public List<PropertyTypeInfo> getPropertyTypeInfos() {
        return propertyTypeInfos;
    }

    public void setPropertyTypeInfos(List<PropertyTypeInfo> propertyTypeInfos) {
        this.propertyTypeInfos = propertyTypeInfos;
    }

    public List<AdditionalDto> getAdditionals() {
        return additionals;
    }

    public void setAdditionals(List<AdditionalDto> additionals) {
        this.additionals = additionals;
    }

    public AssessmentPriceExt getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(AssessmentPriceExt historyPrice) {
        this.historyPrice = historyPrice;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
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

    public GisImgInfo getGisImg() {
        return gisImg;
    }

    public void setGisImg(GisImgInfo gisImg) {
        this.gisImg = gisImg;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getIsSchoolDistrict() {
        return isSchoolDistrict;
    }

    public void setIsSchoolDistrict(String isSchoolDistrict) {
        this.isSchoolDistrict = isSchoolDistrict;
    }
}
