package com.caad.wechat.model.viss.systemAssessment;

import java.util.List;


/**
 * 历史询价查询实体
 */
public class InquiryHistorySearchModel {
    /**
     * 询价人id
     */
    private String assessmentBy;

    /**
     * 客户所属公司
     */
    private long companyId;

    /**
     * 估价机构
     */
    private long sysOrgId;


    private List<Long> sysOrgIds;

    /**
     * 客户id集合
     */
    private List<Long> companyIds;

    /**
     * 二级物业类型
     */
    private int propertyID;

    /**
     * 物业类型
     */
    private String propertyType;

    /**
     * 市
     */
    private long regionCode;

    /**
     * 区县
     */
    private long countyCode;

    /**
     * 搜索栏
     */
    private String text;

    /**
     * 是否已出具询价单
     */
    private boolean isCompleted;

    /**
     * 是否已导入押品
     */
    private boolean intoMortgage;

    /**
     * 省
     */
    private long provinceCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 开始创建时间
     */
    private String startCreateTime;


    /**
     * 结束创建时间
     */
    private String endCreateTime;

    /**
     * 物业地址/所属项目/业务编号/询(估)价机构或人
     */
    private String comCondition;
    /**
     * 详细地址列表
     */
    private List<String> detailAddresses;

    /**
     * 微信用户openId
     */
    private String userOpenId;

    /**
     * 单据来源
     */
    private String source;


    private String weChatComCondition;

    public String getAssessmentBy() {
        return assessmentBy;
    }

    public void setAssessmentBy(String assessmentBy) {
        this.assessmentBy = assessmentBy;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public List<Long> getSysOrgIds() {
        return sysOrgIds;
    }

    public void setSysOrgIds(List<Long> sysOrgIds) {
        this.sysOrgIds = sysOrgIds;
    }

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isIntoMortgage() {
        return intoMortgage;
    }

    public void setIntoMortgage(boolean intoMortgage) {
        this.intoMortgage = intoMortgage;
    }

    public long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getComCondition() {
        return comCondition;
    }

    public void setComCondition(String comCondition) {
        this.comCondition = comCondition;
    }

    public List<String> getDetailAddresses() {
        return detailAddresses;
    }

    public void setDetailAddresses(List<String> detailAddresses) {
        this.detailAddresses = detailAddresses;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWeChatComCondition() {
        return weChatComCondition;
    }

    public void setWeChatComCondition(String weChatComCondition) {
        this.weChatComCondition = weChatComCondition;
    }
}