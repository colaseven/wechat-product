package com.caad.wechat.model.viss.common;

import com.caad.wechat.model.viss.systemAssessment.TemplateTypeModel;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PagedList<T> extends PageInfo {

    @JsonProperty("TotalItemCount")
    private int totalItemCount;

    @JsonProperty("TotalPageCount")
    private int totalPageCount;

    @JsonProperty("Items")
    private List<T> items;

    @JsonProperty("TemplateTypes")
    private List<TemplateTypeModel> templateTypes;

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<TemplateTypeModel> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(List<TemplateTypeModel> templateTypes) {
        this.templateTypes = templateTypes;
    }
}
