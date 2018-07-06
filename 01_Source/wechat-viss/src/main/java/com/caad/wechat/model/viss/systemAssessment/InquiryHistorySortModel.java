package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 历史询价排序实体
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InquiryHistorySortModel {
    /**
     * 询价时间
     */
    @JsonProperty("AssessmentTime")
    private boolean assessmentTime;

    /**
     * 总价
     */
    @JsonProperty("TotalPrice")
    private boolean totalPrice;

    @JsonProperty("Id")
    private boolean id;

    public boolean isAssessmentTime() {
        return assessmentTime;
    }

    public void setAssessmentTime(boolean assessmentTime) {
        this.assessmentTime = assessmentTime;
    }

    public boolean isTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(boolean totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}