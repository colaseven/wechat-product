package com.caad.wechat.model.viss.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class QueryAreaModel {


    /// <summary>
    /// 默认物业id
    /// </summary>
    @JsonProperty("DefaultPropertyTypeId")
    private String DefaultPropertyTypeId;

    /// <summary>
    /// 物业类型
    /// </summary>
    @JsonProperty("PropertyTypes")
    private List<PropertyModelExt> PropertyTypes;


    public String getDefaultPropertyTypeId() {
        return DefaultPropertyTypeId;
    }

    public void setDefaultPropertyTypeId(String defaultPropertyTypeId) {
        DefaultPropertyTypeId = defaultPropertyTypeId;
    }

    public List<PropertyModelExt> getPropertyTypes() {
        return PropertyTypes;
    }

    public void setPropertyTypes(List<PropertyModelExt> propertyTypes) {
        PropertyTypes = propertyTypes;
    }
}


