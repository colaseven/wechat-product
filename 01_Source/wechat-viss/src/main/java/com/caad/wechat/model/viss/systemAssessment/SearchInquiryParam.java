package com.caad.wechat.model.viss.systemAssessment;


public class SearchInquiryParam {

//    private long id;

    /// <summary>
    /// 二级物业id
    /// </summary>
    private String propertyId;

    /// <summary>
    /// 物业类型
    /// </summary>
//    private String propertyTypeCode;

    /// <summary>
    /// 省  从当前省份选择
    /// </summary>
//    private String provinceCode;

    /// <summary>
    /// 市
    /// </summary>
    private String regionCode;

    /// <summary>
    /// 区县
    /// </summary>
//    private String countyCode;

    /// <summary>
    ///<option value="ALL">所有级别</option>
    ///<option value="project">小区级别</option>
    ///<option value="Building">楼幢级别</option>
    ///<option value="Households">户级别</option>
    ///<option value="project||Building">小区楼幢</option>
    ///<option value="Building||Households">户幢级别</option>
    /// </summary>
//    private String level;

    /// <summary>
    /// 小区id
    /// </summary>
    private String projectId;

    /// <summary>
    /// 楼栋Id
    /// </summary>
    private String floorId;

    /// <summary>
    /// 查询的字符
    /// </summary>
    private String name;


    private String floorUnit;

    private String roomUnit;

    /// <summary>
    /// 小区名称
    /// </summary>
//    private String projectName  ;

//    public long getId() {
//        return id;
//    }

//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getPropertyId() {
//        return propertyId;
//    }
//
//    public void setPropertyId(long propertyId) {
//        this.propertyId = propertyId;
//    }
//
//    public String getPropertyTypeCode() {
//        return propertyTypeCode;
//    }
//
//    public void setPropertyTypeCode(String propertyTypeCode) {
//        this.propertyTypeCode = propertyTypeCode;
//    }
//
//    public String getProvinceCode() {
//        return provinceCode;
//    }
//
//    public void setProvinceCode(String provinceCode) {
//        this.provinceCode = provinceCode;
//    }
//
//    public String getRegionCode() {
//        return regionCode;
//    }
//
//    public void setRegionCode(String regionCode) {
//        this.regionCode = regionCode;
//    }
//
//    public String getCountyCode() {
//        return countyCode;
//    }
//
//    public void setCountyCode(String countyCode) {
//        this.countyCode = countyCode;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//
//    public void setLevel(String level) {
//        this.level = level;
//    }
//
//    public long getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(long projectId) {
//        this.projectId = projectId;
//    }
//
//    public long getFloorId() {
//        return floorId;
//    }
//
//    public void setFloorId(long floorId) {
//        this.floorId = floorId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getProjectName() {
//        return projectName;
//    }
//
//    public void setProjectName(String projectName) {
//        this.projectName = projectName;
//    }


    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
