package com.caad.wechat.model.viss.systemAssessment;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class UiTemplate {

    @JsonProperty("ValueType")
    private int valueType;

    @JsonProperty("DisplayName")
    private String displayName;

    @JsonProperty("OrderById")
    private int orderById;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("Placeholder")
    private String placeholder;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("DataSource")
    private List<DataItem> dataSource;

    @JsonProperty("MultiSelect")
    private boolean multiSelect;

    @JsonProperty("IsRequired")
    private boolean isRequired;

    @JsonProperty("DisplayType")
    private String displayType;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Value")
    private Object value;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("id")
    private long id;

    @JsonProperty("FieldName")
    private String fieldName;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("IsDisplay")
    private boolean isDisplay;

    @JsonProperty("IsCondition")
    private boolean isCondition;

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getOrderById() {
        return orderById;
    }

    public void setOrderById(int orderById) {
        this.orderById = orderById;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<DataItem> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<DataItem> dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public boolean isCondition() {
        return isCondition;
    }

    public void setCondition(boolean condition) {
        isCondition = condition;
    }
}
