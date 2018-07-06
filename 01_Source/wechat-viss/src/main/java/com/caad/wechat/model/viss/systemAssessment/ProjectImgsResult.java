package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectImgsResult {
    /**
     * 图片名称
     */
    @JsonProperty("FileName")
    private String fileName;

    /**
     * 图片路径
     */
    @JsonProperty("FileUrl")
    private String fileUrl;


    /**
     * 缩略图
     */
    @JsonProperty("Thumbnail")
    private String thumbnail;


    /**
     * 附件类别
     */
    @JsonProperty("FileType")
    private String fileType;
    /**
     * 图片类别Id
     */
    @JsonProperty("PictureType")
    private long pictureType;

    /**
     * 图片类别名称
     */
    @JsonProperty("PictureTypeName")
    private String pictureTypeName;

    /**
     * 图片说明
     */
    @JsonProperty("Description")
    private String description;

    /**
     * 是否审核 -true=审核，false=未审核
     */
    @JsonProperty("Examine")
    private boolean examine;

    /**
     * 排序
     */
    @JsonProperty("Seq")
    private int seq;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getPictureType() {
        return pictureType;
    }

    public void setPictureType(long pictureType) {
        this.pictureType = pictureType;
    }

    public String getPictureTypeName() {
        return pictureTypeName;
    }

    public void setPictureTypeName(String pictureTypeName) {
        this.pictureTypeName = pictureTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExamine() {
        return examine;
    }

    public void setExamine(boolean examine) {
        this.examine = examine;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}