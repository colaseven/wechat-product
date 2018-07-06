package com.caad.wechat.model.viss.systemAssessment;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInfoResult {
    /**
     * 小区名称
     */
    @JsonProperty("ProjectName")
    private String projectName;

    /**
     * 图片
     */
    @JsonProperty("Imgs")
    private List<ProjectImgsResult> imgs;

    /**
     * 图片分类
     */
    @JsonProperty("ImgGroups")
    private List<DataPictureTypeResult> imgGroups;

    /**
     * 小区详情数据
     */
    @JsonProperty("ProjectData")
    private ResidentProjectGroupInfoResult projectData;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ProjectImgsResult> getImgs() {
        return imgs;
    }

    public void setImgs(List<ProjectImgsResult> imgs) {
        this.imgs = imgs;
    }

    public List<DataPictureTypeResult> getImgGroups() {
        return imgGroups;
    }

    public void setImgGroups(List<DataPictureTypeResult> imgGroups) {
        this.imgGroups = imgGroups;
    }

    public ResidentProjectGroupInfoResult getProjectData() {
        return projectData;
    }

    public void setProjectData(ResidentProjectGroupInfoResult projectData) {
        this.projectData = projectData;
    }
}