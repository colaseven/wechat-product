package com.caad.wechat.model.viss.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/// <summary>
/// 客户可用地区
/// </summary>
@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class AreaModelExt {
    /// <summary>
    /// id
    /// </summary>
    @JsonProperty("Id")
    private String id;

    /// <summary>
    /// 地区名字
    /// </summary>
    @JsonProperty("Name")
    private String name;

    /// <summary>
    /// 父级
    /// </summary>
    @JsonProperty("ParentId")
    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
