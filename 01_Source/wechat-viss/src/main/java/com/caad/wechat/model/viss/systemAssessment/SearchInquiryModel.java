package com.caad.wechat.model.viss.systemAssessment;


public class SearchInquiryModel {

    private String propertyTypeCode;

//    private String provinceCode;

    private String regionCode;

//    private String countyCode;

    private String name;

//    private int pageIndex;

    private int pageSize;

//    private int skipCount;


    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
