package com.caad.wechat.model.viss.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductBoughtHistoryModel {
    @JsonProperty("ProductResidueCount")
    private List<ProductResidueCount> productResidueCount;

    @JsonProperty("BoughtList")
    private List<ProductBoughtModel> boughtList;

    public List<ProductResidueCount> getProductResidueCount() {
        return productResidueCount;
    }

    public void setProductResidueCount(List<ProductResidueCount> productResidueCount) {
        this.productResidueCount = productResidueCount;
    }

    public List<ProductBoughtModel> getBoughtList() {
        return boughtList;
    }

    public void setBoughtList(List<ProductBoughtModel> boughtList) {
        this.boughtList = boughtList;
    }
}
