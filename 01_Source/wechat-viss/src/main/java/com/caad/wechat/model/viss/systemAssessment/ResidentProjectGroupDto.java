package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 询价-小区信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidentProjectGroupDto {
    /**
     * ID
     */
    @JsonProperty("ID")
    private long iD;

    /**
     * 字段ID
     */
    @JsonProperty("FieldID")
    private long fieldID;

    /**
     * 字段排序
     */
    @JsonProperty("FieldIndex")
    private long fieldIndex;

    /**
     * 是否必选
     */
    @JsonProperty("IsChoose")
    private boolean isChoose;

    /**
     * 是否推送
     */
    @JsonProperty("IsSend")
    private boolean isSend;

    /**
     * 是否必填
     */
    @JsonProperty("IsFill")
    private boolean isFill;

    /**
     * 组ID
     */
    @JsonProperty("GroupID")
    private long groupID;

    /**
     * 字段长度
     */
    @JsonProperty("FieldLength")
    private long fieldLength;

    /**
     * 是否只读
     */
    @JsonProperty("IsReadOnly")
    private boolean isReadOnly;

    /**
     * 默认值
     */
    @JsonProperty("DefaultValue")
    private String defaultValue;

    /**
     * 字段值
     */
    @JsonProperty("FieldValue")
    private String fieldValue;

    /**
     * 级联等级
     */
    @JsonProperty("FieldValueLevel")
    private long fieldValueLevel;

    /**
     * 字段中文名
     */
    @JsonProperty("FieldNameCn")
    private String fieldNameCn;

    /**
     * 字段英文名
     */
    @JsonProperty("FieldNameEn")
    private String fieldNameEn;

    /**
     * 字段类型
     */
    @JsonProperty("FieldType")
    private String fieldType;

    /**
     * 约束条件
     */
    @JsonProperty("FieldConstraint")
    private String fieldConstraint;

    /**
     * 是否作为查询条件
     */
    @JsonProperty("IsWhere")
    private boolean isWhere;

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public long getFieldID() {
        return fieldID;
    }

    public void setFieldID(long fieldID) {
        this.fieldID = fieldID;
    }

    public long getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(long fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill) {
        isFill = fill;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public long getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(long fieldLength) {
        this.fieldLength = fieldLength;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public long getFieldValueLevel() {
        return fieldValueLevel;
    }

    public void setFieldValueLevel(long fieldValueLevel) {
        this.fieldValueLevel = fieldValueLevel;
    }

    public String getFieldNameCn() {
        return fieldNameCn;
    }

    public void setFieldNameCn(String fieldNameCn) {
        this.fieldNameCn = fieldNameCn;
    }

    public String getFieldNameEn() {
        return fieldNameEn;
    }

    public void setFieldNameEn(String fieldNameEn) {
        this.fieldNameEn = fieldNameEn;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldConstraint() {
        return fieldConstraint;
    }

    public void setFieldConstraint(String fieldConstraint) {
        this.fieldConstraint = fieldConstraint;
    }

    public boolean isWhere() {
        return isWhere;
    }

    public void setWhere(boolean where) {
        isWhere = where;
    }
}