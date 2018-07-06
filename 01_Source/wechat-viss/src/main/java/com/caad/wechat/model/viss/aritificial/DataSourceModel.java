package com.caad.wechat.model.viss.aritificial;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSourceModel {
    @JsonProperty("Index")
    private int index;

    @JsonProperty("Value")
    private String value;

    @JsonProperty("Text")
    private String text;

    @JsonProperty("Class")
    private String class1;

    @JsonProperty("IsCurrent")
    private Boolean isCurrent;

    @JsonProperty("ParentValue")
    private String parentValue;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass(String class1) {
        this.class1 = class1;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue;
    }
}
