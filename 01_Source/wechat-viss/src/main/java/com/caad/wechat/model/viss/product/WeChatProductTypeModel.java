package com.caad.wechat.model.viss.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatProductTypeModel {

    @JsonProperty("ModelTemplate")
    private long modelTemplate;

    @JsonProperty("ModelTemplateName")
    private String modelTemplateName;

    @JsonProperty("List")
    private List<WeChatProductsModel> list;

    @JsonProperty("GiveTimes")
    private String giveTimes;

    public long getModelTemplate() {
        return modelTemplate;
    }

    public void setModelTemplate(long modelTemplate) {
        this.modelTemplate = modelTemplate;
    }

    public String getModelTemplateName() {
        return modelTemplateName;
    }

    public void setModelTemplateName(String modelTemplateName) {
        this.modelTemplateName = modelTemplateName;
    }

    public List<WeChatProductsModel> getList() {
        return list;
    }

    public void setList(List<WeChatProductsModel> list) {
        this.list = list;
    }

    public String getGiveTimes() {
        return giveTimes;
    }

    public void setGiveTimes(String giveTimes) {
        this.giveTimes = giveTimes;
    }
}
