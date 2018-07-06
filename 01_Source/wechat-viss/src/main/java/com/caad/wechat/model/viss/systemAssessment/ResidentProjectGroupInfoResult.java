package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidentProjectGroupInfoResult {
    /**
     * 分组集合
     */
    @JsonProperty("GroupList")
    private List<DataBaseInfoGroupDto> groupList;

    /**
     * 分组字段集合
     */
    @JsonProperty("GroupFeildList")
    private List<ResidentProjectGroupDto> groupFeildList;

    public List<DataBaseInfoGroupDto> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<DataBaseInfoGroupDto> groupList) {
        this.groupList = groupList;
    }

    public List<ResidentProjectGroupDto> getGroupFeildList() {
        return groupFeildList;
    }

    public void setGroupFeildList(List<ResidentProjectGroupDto> groupFeildList) {
        this.groupFeildList = groupFeildList;
    }
}