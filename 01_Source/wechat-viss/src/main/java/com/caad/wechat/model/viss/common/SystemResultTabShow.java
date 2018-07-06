package com.caad.wechat.model.viss.common;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class SystemResultTabShow {
    @JsonProperty("Id")
    private String id;

    @JsonProperty("OrganizeId")
    private String organizeId;

    @JsonProperty("CusOrganizeId")
    private String cusOrganizeId;

    @JsonProperty("ShowResult")
    private String showResult;


    @JsonProperty("ShowResidentInfo")
    private String showResidentInfo;


    @JsonProperty("ShowResidentImg")
    private String showResidentImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getCusOrganizeId() {
        return cusOrganizeId;
    }

    public void setCusOrganizeId(String cusOrganizeId) {
        this.cusOrganizeId = cusOrganizeId;
    }

    public String getShowResult() {
        return showResult;
    }

    public void setShowResult(String showResult) {
        this.showResult = showResult;
    }

    public String getShowResidentInfo() {
        return showResidentInfo;
    }

    public void setShowResidentInfo(String showResidentInfo) {
        this.showResidentInfo = showResidentInfo;
    }

    public String getShowResidentImg() {
        return showResidentImg;
    }

    public void setShowResidentImg(String showResidentImg) {
        this.showResidentImg = showResidentImg;
    }
}
