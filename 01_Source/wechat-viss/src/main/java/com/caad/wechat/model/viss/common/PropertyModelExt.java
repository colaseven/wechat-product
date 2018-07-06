package com.caad.wechat.model.viss.common;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

/// <summary>
/// 客户可用物业类型
/// </summary>
public class PropertyModelExt {
    @JsonProperty("PropertyTypeId")
    private String propertyTypeId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("DefaultAreaId")
    private String defaultAreaId;

    @JsonProperty("Areas")
    private Map<String, List<AreaModelExt>> Areas;

    @JsonProperty("TemplateTypes")
    private List<ProductList> templateTypes;

    public String getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(String propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultAreaId() {
        return defaultAreaId;
    }

    public void setDefaultAreaId(String defaultAreaId) {
        this.defaultAreaId = defaultAreaId;
    }

    public Map<String, List<AreaModelExt>> getAreas() {
        return Areas;
    }

    public void setAreas(Map<String, List<AreaModelExt>> areas) {
        Areas = areas;
    }

    public List<ProductList> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(List<ProductList> templateTypes) {
        this.templateTypes = templateTypes;
    }
}
