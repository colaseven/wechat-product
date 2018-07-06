package com.caad.wechat.model.viss.systemAssessment;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 询价-小区信息分组
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBaseInfoGroupDto {
    /**
     * 分组Id
     */
    @JsonProperty("ID")
    private long iD;

    /**
     * 组名
     */
    @JsonProperty("GroupName")
    private String groupName;

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}