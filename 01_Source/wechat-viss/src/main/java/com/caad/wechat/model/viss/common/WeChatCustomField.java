package com.caad.wechat.model.viss.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class WeChatCustomField {

    @JsonProperty("Name")
    private String name;


    @JsonProperty("Value")
    private String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
