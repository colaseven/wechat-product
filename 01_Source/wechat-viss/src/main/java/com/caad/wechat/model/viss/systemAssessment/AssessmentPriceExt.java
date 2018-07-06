package com.caad.wechat.model.viss.systemAssessment;


import com.caad.wechat.model.viss.common.SystemResultTabShow;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class AssessmentPriceExt {

    /// <summary>
    /// 界面展示模板
    /// </summary>
    @JsonProperty("Codens")
    private List<UiTemplate> codens;

    /// <summary>
    /// 界面展示模板
    /// </summary>
    @JsonProperty("PriceInfo")
    private AssessmentPriceInfoExt priceInfo;

    @JsonProperty("WeChatResultDto")
    private SystemResultTabShow weChatResultDto;


    @JsonProperty("UserCollocation")
    private String userCollocation;

    @JsonProperty("CommunityId")
    private String communityId;


    @JsonProperty("TemplateTypes")
    private List<TemplateTypeModel> templateTypes;


    public SystemResultTabShow getWeChatResultDto() {
        return weChatResultDto;
    }

    public void setWeChatResultDto(SystemResultTabShow weChatResultDto) {
        this.weChatResultDto = weChatResultDto;
    }

    public String getUserCollocation() {
        return userCollocation;
    }

    public void setUserCollocation(String userCollocation) {
        this.userCollocation = userCollocation;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public List<TemplateTypeModel> getTemplateTypes() {
        return templateTypes;
    }

    public void setTemplateTypes(List<TemplateTypeModel> templateTypes) {
        this.templateTypes = templateTypes;
    }

    public List<UiTemplate> getCodens() {
        return codens;
    }

    public void setCodens(List<UiTemplate> codens) {
        this.codens = codens;
    }

    public AssessmentPriceInfoExt getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(AssessmentPriceInfoExt priceInfo) {
        this.priceInfo = priceInfo;
    }
}
