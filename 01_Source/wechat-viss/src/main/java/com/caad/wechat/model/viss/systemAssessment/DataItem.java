package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class DataItem {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private Object value;
    @JsonProperty("PropertyType4")
    private String propertyType4;
    @JsonProperty("PropertyType3")
    private String propertyType3;
    @JsonProperty("Description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getPropertyType4() {
        return propertyType4;
    }

    public void setPropertyType4(String propertyType4) {
        this.propertyType4 = propertyType4;
    }

    public String getPropertyType3() {
        return propertyType3;
    }

    public void setPropertyType3(String propertyType3) {
        this.propertyType3 = propertyType3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
