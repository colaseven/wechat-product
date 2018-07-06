package com.caad.wechat.model.viss.common;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class CompanyDetail {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("OrganizeId")
    private String organizeId;
    @JsonProperty("CusOrganizeId")
    private String cusOrganizeId;
    @JsonProperty("CompanyProfile")
    private String companyProfile;

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

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }
}
