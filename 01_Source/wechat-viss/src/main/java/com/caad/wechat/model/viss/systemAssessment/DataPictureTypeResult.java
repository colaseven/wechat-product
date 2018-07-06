package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPictureTypeResult {
    /**
     * 小区图片分类id
     */
    @JsonProperty("Id")
    private long id;

    /**
     * 分类名称
     */
    @JsonProperty("Name")
    private String name;

    @JsonProperty("RangeType")
    private String rangeType;

    @JsonProperty("FileType")
    private String fileType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}