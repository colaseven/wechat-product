package com.caad.wechat.model.viss.systemAssessment;

import java.util.List;

public class AssessmentResult {

    private long productId;

    private long id;

    /**
     * 评估参数--二级物业类型 只读
     */
    private long propertyID;

    /**
     * 二级物业类型名称 只读
     */
    private String propertyName;

    /**
     * 三级物业类型名称 只读
     */
    private String thirdPropertyName;

    /**
     * 询价人名称 只读
     */
    private String assessmentByName;

    /**
     * 区域对应的采集机构Id
     */
    private long organizeId;

    /**
     * 区域对应的采集机构名称
     */
    private String organizeName;

    /**
     * 询价人电话 只读
     */
    private String assessmentTel;

    /**
     * 估价机构名称-客户对应估价机构名字 只读
     */
    private String institutionsName;

    /**
     * 估价机构地址 只读
     */
    private String institutionsAddress;

    /**
     * 估价机构电话 只读
     */
    private String institutionsTel;

    /**
     * 四级物业 页面物业类型切换 输入
     */
    private String propertyTypeCode;

    /**
     * 评估参数-地区 只读
     */
    private long areaID;

    /**
     * 评估参数-SettingType=107 只读
     */
    private int settingType;

    /**
     * 评估参数-评估公式 只读
     */
    private int methodID;

    /**
     * 小区名称 小区/楼栋/户(页面输入或选择) 输入
     */
    private String projectName;

    /**
     * 地址 输入
     */
    private String address;

    /**
     * 小区名址|楼栋名址|户名址  只读
     */
    private String systemAddress;

    /**
     * 小区Id 输入
     */
    private long communityId;

    /**
     * 楼栋Id 输入
     */
    private long unitId;

    /**
     * 楼栋名 输入
     */
    private String unitName;

    /**
     * 室号Id 输入
     */
    private long roomNo;

    /**
     * 室名 输入
     */
    private String roomName;

    /**
     * 物业类型 输入
     */
    private String propertyTypeId;
    /**
     * 物业类型名称 输入
     */
    private String propertyTypeName;

    /**
     * 所在楼层 输入
     */
    private List<Integer> floorId;

    /**
     * 起始，总楼层 输入
     */
    private List<Integer> totalFloor;
    /**
     * 面积 输入
     */
    private String structureArea;

    /**
     * 朝向 输入
     */
    private String toward;

    /**
     * 副朝向 输入
     */
    private String viceToward;

    /**
     * 竣工年份 输入
     */
    private String completionYear;

    /**
     * 户型 输入
     */
    private String houseType;

    /**
     * 景观资源 输入
     */
    private List<String> landscape;

    /**
     * 装修标准 输入
     */
    private String decorationType;

    /**
     * 装修年份 输入
     */
    private String decorationYear;

    /**
     * 保养程度 输入
     */
    private String maintain;

    /**
     * 有无电梯 输入
     */
    private String havingLift;

    /**
     * 供气
     */
    private String supplyGas;
    /**
     * 有无泳池 输入
     */
    private String havingSwimmingPool;

    /**
     * 有无花园 输入
     */
    private String gardenSize;
    /**
     * 有无供暖
     */
    private String havingHeating;

    /**
     * 有无天井
     */
    private String havingPatio;

    /**
     * 有无露台
     */
    private String havingTerrace;

    /**
     * 是否学区房
     */
    private String isSchoolDistrict;

    /**
     * 建筑形态
     */
    private String architecturalForm;

    /**
     * 建筑结构
     */
    private String architecturalConstruction;

    /**
     * 房屋结构
     */
    private String housingType;

    /**
     * 立柱--写字楼 输入
     */
    private List<String> crutch;

    /**
     * 其它因素 输入
     */
    private List<String> others;



    /**
     * 评估单价(包括有产权附房) 只读
     */
    private double unitPrice;

    /**
     * 中文大写总价
     */
    private String totalPriceChar;

    /**
     * 中文大写单价
     */
    private String unitPriceChar;

    /**
     * 住宅评估单价 只读
     */
    private double houseUnitPrice;

    /**
     * 住宅评估总价 只读
     */
    private double houseTotalPrice;

    /**
     * 有产权的辅房总价 只读
     */
    private double propertyRightsPrice;

    /**
     * 无产权的辅房总价 只读
     */
    private double propertyFreePrice;


    /**
     * 装修单价 只读
     */
    private double decorationUnitPrice;

    /**
     * 装修总价 只读
     */
    private double decorationTotalPrice;

    /**
     * 是否高于-楼栋基准价 只读
     */
    private boolean unitBenchmarkPriceAbove;

    /**
     * 楼栋基准价 只读
     */
    private double unitBenchmarkPrice;

    /**
     * 基准价来源
     */
    private String basePriceSource;

    /**
     * 所用基准价
     */
    private double basicPrice;

    /**
     * 是否高于-周边小区均价 只读
     */
    private boolean projectAveragePriceAbove;

    /**
     * 周边小区均价 只读
     */
    private double projectAveragePrice;

    /**
     * 是否高于-小区基准价 只读
     */
    private boolean projectBenchmarkPriceAbove;

    /**
     * 小区基准价 只读
     */
    private double projectBenchmarkPrice;

    /**
     * 询价时间 只读
     */
    private String assessmentTime;

    /**
     * 询价人 只读
     */
    private String assessmentBy;

    /**
     * 帐号
     */
    private String createAcount;

    /**
     * 附房数据 输入
     */
    private List<AdditionalDto> additionals;

    /**
     * 是否已出具询价单 只读
     */
    private boolean isCompleted;

    /**
     * 询价人客户所在估价机构 只读
     */
    private long institutionsId;

    /**
     * 省 只读
     */
    private long provinceCode;

    /**
     * 市 只读
     */
    private long regionCode;

    /**
     * 区县 只读
     */
    private long countyCode;

    /**
     * 询价单号 只读
     */
    private String number;

    /**
     * 分发单据id
     */
    private String caseId;

    /**
     * 客户所属公司 只读
     */
    private long companyId;

    /**
     * 客户所属公司名称
     */
    private String companyName;

    /**
     * 询价结果类型，0未设置，1抵押评估价格，2合理价格区间
     */
    private int priceResultType;

    /**
     * 是否为总价
     */
    private String isTotalPrice;


    /**
     * 评估总价(包括有产权附房) 只读
     */
    private double totalPrice;

    /**
     * 是否已付费风险评级
     */
    private boolean isPaidRisk;

    /**
     * 价格上限
     */
    private double priceHighProportion;

    /**
     * 价格下限
     */
    private double priceLowProportion;

    /**
     * 价格上限-总价
     */
    private double totalPriceHighProportion;

    /**
     * 价格下限-总价
     */
    private double totalPriceLowProportion;

    /**
     * 询价目的
     */
    private String assessPurpose;

    /**
     * 共有情况
     */
    private String contains;

    /**
     * 产权人
     */
    private String owner;

    /**
     * 产权证号
     */
    private String propertyRightNo;

    /**
     * 证载用途
     */
    private String planningPurpose;

    /**
     * 实际用途
     */
    private String practicalUse;

    /**
     * Sessiongid（系统询价页面初始化时返回的参数，必传）
     */
    private String sessionGuid;

    /**
     * 微信专用
     */
    private String userOpenId;

    /**
     * 公众号id
     */
    private String appId;


    private String towardId;


    private String viceTowardId;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 微信昵称
     */
    private String weChatNickName;

    /**
     * 微信公众号名称
     */
    private String weChatAppName;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(long propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getThirdPropertyName() {
        return thirdPropertyName;
    }

    public void setThirdPropertyName(String thirdPropertyName) {
        this.thirdPropertyName = thirdPropertyName;
    }

    public String getAssessmentByName() {
        return assessmentByName;
    }

    public void setAssessmentByName(String assessmentByName) {
        this.assessmentByName = assessmentByName;
    }

    public long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(long organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getAssessmentTel() {
        return assessmentTel;
    }

    public void setAssessmentTel(String assessmentTel) {
        this.assessmentTel = assessmentTel;
    }

    public String getInstitutionsName() {
        return institutionsName;
    }

    public void setInstitutionsName(String institutionsName) {
        this.institutionsName = institutionsName;
    }

    public String getInstitutionsAddress() {
        return institutionsAddress;
    }

    public void setInstitutionsAddress(String institutionsAddress) {
        this.institutionsAddress = institutionsAddress;
    }

    public String getInstitutionsTel() {
        return institutionsTel;
    }

    public void setInstitutionsTel(String institutionsTel) {
        this.institutionsTel = institutionsTel;
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

    public int getMethodID() {
        return methodID;
    }

    public void setMethodID(int methodID) {
        this.methodID = methodID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemAddress() {
        return systemAddress;
    }

    public void setSystemAddress(String systemAddress) {
        this.systemAddress = systemAddress;
    }

    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(long communityId) {
        this.communityId = communityId;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(long roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
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

    public List<Integer> getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(List<Integer> totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getStructureArea() {
        return structureArea;
    }

    public void setStructureArea(String structureArea) {
        this.structureArea = structureArea;
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward;
    }

    public String getViceToward() {
        return viceToward;
    }

    public void setViceToward(String viceToward) {
        this.viceToward = viceToward;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public List<String> getLandscape() {
        return landscape;
    }

    public void setLandscape(List<String> landscape) {
        this.landscape = landscape;
    }

    public String getDecorationType() {
        return decorationType;
    }

    public void setDecorationType(String decorationType) {
        this.decorationType = decorationType;
    }

    public String getDecorationYear() {
        return decorationYear;
    }

    public void setDecorationYear(String decorationYear) {
        this.decorationYear = decorationYear;
    }

    public String getMaintain() {
        return maintain;
    }

    public void setMaintain(String maintain) {
        this.maintain = maintain;
    }

    public String getHavingLift() {
        return havingLift;
    }

    public void setHavingLift(String havingLift) {
        this.havingLift = havingLift;
    }

    public String getSupplyGas() {
        return supplyGas;
    }

    public void setSupplyGas(String supplyGas) {
        this.supplyGas = supplyGas;
    }

    public String getHavingSwimmingPool() {
        return havingSwimmingPool;
    }

    public void setHavingSwimmingPool(String havingSwimmingPool) {
        this.havingSwimmingPool = havingSwimmingPool;
    }

    public String getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(String gardenSize) {
        this.gardenSize = gardenSize;
    }

    public String getHavingHeating() {
        return havingHeating;
    }

    public void setHavingHeating(String havingHeating) {
        this.havingHeating = havingHeating;
    }

    public String getHavingPatio() {
        return havingPatio;
    }

    public void setHavingPatio(String havingPatio) {
        this.havingPatio = havingPatio;
    }

    public String getHavingTerrace() {
        return havingTerrace;
    }

    public void setHavingTerrace(String havingTerrace) {
        this.havingTerrace = havingTerrace;
    }

    public String getIsSchoolDistrict() {
        return isSchoolDistrict;
    }

    public void setIsSchoolDistrict(String isSchoolDistrict) {
        this.isSchoolDistrict = isSchoolDistrict;
    }

    public String getArchitecturalForm() {
        return architecturalForm;
    }

    public void setArchitecturalForm(String architecturalForm) {
        this.architecturalForm = architecturalForm;
    }

    public String getArchitecturalConstruction() {
        return architecturalConstruction;
    }

    public void setArchitecturalConstruction(String architecturalConstruction) {
        this.architecturalConstruction = architecturalConstruction;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public List<String> getCrutch() {
        return crutch;
    }

    public void setCrutch(List<String> crutch) {
        this.crutch = crutch;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPriceChar() {
        return totalPriceChar;
    }

    public void setTotalPriceChar(String totalPriceChar) {
        this.totalPriceChar = totalPriceChar;
    }

    public String getUnitPriceChar() {
        return unitPriceChar;
    }

    public void setUnitPriceChar(String unitPriceChar) {
        this.unitPriceChar = unitPriceChar;
    }

    public double getHouseUnitPrice() {
        return houseUnitPrice;
    }

    public void setHouseUnitPrice(double houseUnitPrice) {
        this.houseUnitPrice = houseUnitPrice;
    }

    public double getHouseTotalPrice() {
        return houseTotalPrice;
    }

    public void setHouseTotalPrice(double houseTotalPrice) {
        this.houseTotalPrice = houseTotalPrice;
    }

    public double getPropertyRightsPrice() {
        return propertyRightsPrice;
    }

    public void setPropertyRightsPrice(double propertyRightsPrice) {
        this.propertyRightsPrice = propertyRightsPrice;
    }

    public double getPropertyFreePrice() {
        return propertyFreePrice;
    }

    public void setPropertyFreePrice(double propertyFreePrice) {
        this.propertyFreePrice = propertyFreePrice;
    }

    public double getDecorationUnitPrice() {
        return decorationUnitPrice;
    }

    public void setDecorationUnitPrice(double decorationUnitPrice) {
        this.decorationUnitPrice = decorationUnitPrice;
    }

    public double getDecorationTotalPrice() {
        return decorationTotalPrice;
    }

    public void setDecorationTotalPrice(double decorationTotalPrice) {
        this.decorationTotalPrice = decorationTotalPrice;
    }

    public boolean isUnitBenchmarkPriceAbove() {
        return unitBenchmarkPriceAbove;
    }

    public void setUnitBenchmarkPriceAbove(boolean unitBenchmarkPriceAbove) {
        this.unitBenchmarkPriceAbove = unitBenchmarkPriceAbove;
    }

    public double getUnitBenchmarkPrice() {
        return unitBenchmarkPrice;
    }

    public void setUnitBenchmarkPrice(double unitBenchmarkPrice) {
        this.unitBenchmarkPrice = unitBenchmarkPrice;
    }

    public String getBasePriceSource() {
        return basePriceSource;
    }

    public void setBasePriceSource(String basePriceSource) {
        this.basePriceSource = basePriceSource;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public boolean isProjectAveragePriceAbove() {
        return projectAveragePriceAbove;
    }

    public void setProjectAveragePriceAbove(boolean projectAveragePriceAbove) {
        this.projectAveragePriceAbove = projectAveragePriceAbove;
    }

    public double getProjectAveragePrice() {
        return projectAveragePrice;
    }

    public void setProjectAveragePrice(double projectAveragePrice) {
        this.projectAveragePrice = projectAveragePrice;
    }

    public boolean isProjectBenchmarkPriceAbove() {
        return projectBenchmarkPriceAbove;
    }

    public void setProjectBenchmarkPriceAbove(boolean projectBenchmarkPriceAbove) {
        this.projectBenchmarkPriceAbove = projectBenchmarkPriceAbove;
    }

    public double getProjectBenchmarkPrice() {
        return projectBenchmarkPrice;
    }

    public void setProjectBenchmarkPrice(double projectBenchmarkPrice) {
        this.projectBenchmarkPrice = projectBenchmarkPrice;
    }

    public String getAssessmentTime() {
        return assessmentTime;
    }

    public void setAssessmentTime(String assessmentTime) {
        this.assessmentTime = assessmentTime;
    }

    public String getAssessmentBy() {
        return assessmentBy;
    }

    public void setAssessmentBy(String assessmentBy) {
        this.assessmentBy = assessmentBy;
    }

    public String getCreateAcount() {
        return createAcount;
    }

    public void setCreateAcount(String createAcount) {
        this.createAcount = createAcount;
    }

    public List<AdditionalDto> getAdditionals() {
        return additionals;
    }

    public void setAdditionals(List<AdditionalDto> additionals) {
        this.additionals = additionals;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getInstitutionsId() {
        return institutionsId;
    }

    public void setInstitutionsId(long institutionsId) {
        this.institutionsId = institutionsId;
    }

    public long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public long getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(long regionCode) {
        this.regionCode = regionCode;
    }

    public long getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(long countyCode) {
        this.countyCode = countyCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPriceResultType() {
        return priceResultType;
    }

    public void setPriceResultType(int priceResultType) {
        this.priceResultType = priceResultType;
    }


    public void setIsTotalPrice(boolean isTotalPrice) {
        isTotalPrice = isTotalPrice;
    }

    public boolean isPaidRisk() {
        return isPaidRisk;
    }

    public void setPaidRisk(boolean paidRisk) {
        isPaidRisk = paidRisk;
    }

    public double getPriceHighProportion() {
        return priceHighProportion;
    }

    public void setPriceHighProportion(double priceHighProportion) {
        this.priceHighProportion = priceHighProportion;
    }

    public double getPriceLowProportion() {
        return priceLowProportion;
    }

    public void setPriceLowProportion(double priceLowProportion) {
        this.priceLowProportion = priceLowProportion;
    }

    public double getTotalPriceHighProportion() {
        return totalPriceHighProportion;
    }

    public void setTotalPriceHighProportion(double totalPriceHighProportion) {
        this.totalPriceHighProportion = totalPriceHighProportion;
    }

    public double getTotalPriceLowProportion() {
        return totalPriceLowProportion;
    }

    public void setTotalPriceLowProportion(double totalPriceLowProportion) {
        this.totalPriceLowProportion = totalPriceLowProportion;
    }

    public String getAssessPurpose() {
        return assessPurpose;
    }

    public void setAssessPurpose(String assessPurpose) {
        this.assessPurpose = assessPurpose;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPropertyRightNo() {
        return propertyRightNo;
    }

    public void setPropertyRightNo(String propertyRightNo) {
        this.propertyRightNo = propertyRightNo;
    }

    public String getPlanningPurpose() {
        return planningPurpose;
    }

    public void setPlanningPurpose(String planningPurpose) {
        this.planningPurpose = planningPurpose;
    }

    public String getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse;
    }

    public String getSessionGuid() {
        return sessionGuid;
    }

    public void setSessionGuid(String sessionGuid) {
        this.sessionGuid = sessionGuid;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIsTotalPrice() {
        return isTotalPrice;
    }

    public void setIsTotalPrice(String isTotalPrice) {
        this.isTotalPrice = isTotalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTowardId() {
        return towardId;
    }

    public void setTowardId(String towardId) {
        this.towardId = towardId;
    }

    public String getViceTowardId() {
        return viceTowardId;
    }

    public void setViceTowardId(String viceTowardId) {
        this.viceTowardId = viceTowardId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWeChatNickName() {
        return weChatNickName;
    }

    public void setWeChatNickName(String weChatNickName) {
        this.weChatNickName = weChatNickName;
    }

    public String getWeChatAppName() {
        return weChatAppName;
    }

    public void setWeChatAppName(String weChatAppName) {
        this.weChatAppName = weChatAppName;
    }
}