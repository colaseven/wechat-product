package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class GisImgInfo {
    /// <summary>
    ///     小区id
    /// </summary>
    @JsonProperty("ProjectId")
    private long projectId;

    /// <summary>
    ///     小区默认图
    /// </summary>
    @JsonProperty("ImgPath")
    private String imgPath;

    /// <summary>
    ///     gis落点
    /// </summary>
    @JsonProperty("Point")
    private String point;

    /// <summary>
    ///     省
    /// </summary>
    @JsonProperty("provinceCode")
    private String provinceCode;

    /// <summary>
    ///     市
    /// </summary>
    @JsonProperty("regionCode")
    private String regionCode;

    /// <summary>
    ///     区县
    /// </summary>
    @JsonProperty("countyCode")
    private String countyCode;

    /// <summary>
    ///     小区名称
    /// </summary>
    @JsonProperty("Name")
    private String name;

    /// <summary>
    /// 页面绑定
    /// </summary>
    @JsonProperty("ProjectName")
    private String projectName;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
