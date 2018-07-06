package com.caad.wechat.model.viss.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // 暂时过滤数据项。
public class SysMakePriceTemplateResult {

    @JsonProperty("id")
    private long id;

    @JsonProperty("OrganizeId")
    private long organizeId;

    @JsonProperty("OrganizeName")
    private String organizeName;

    @JsonProperty("propertyId")
    private long propertyId;

    @JsonProperty("PropertyName")
    private String propertyName;

    @JsonProperty("TemplateTypeId")
    private long templateTypeId;

    @JsonProperty("TemplateTypeName")
    private String templateTypeName;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("IsEnable")
    private boolean isEnable;

    @JsonProperty("CreateUser")
    private long createUser;

    @JsonProperty("CreateDate")
    private String createDate;

    @JsonProperty("EditUser")
    private long editUser;

    @JsonProperty("EditDate")
    private String editDate;

    @JsonProperty("UserImage")
    private boolean userImage;

    @JsonProperty("PreviewImage")
    private boolean previewImage;

    @JsonProperty("StampImage")
    private boolean stampImage;

    @JsonProperty("IncludeTax")
    private boolean includeTax;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(long organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public long getTemplateTypeId() {
        return templateTypeId;
    }

    public void setTemplateTypeId(long templateTypeId) {
        this.templateTypeId = templateTypeId;
    }

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getEditUser() {
        return editUser;
    }

    public void setEditUser(long editUser) {
        this.editUser = editUser;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public boolean isUserImage() {
        return userImage;
    }

    public void setUserImage(boolean userImage) {
        this.userImage = userImage;
    }

    public boolean isPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(boolean previewImage) {
        this.previewImage = previewImage;
    }

    public boolean isStampImage() {
        return stampImage;
    }

    public void setStampImage(boolean stampImage) {
        this.stampImage = stampImage;
    }

    public boolean isIncludeTax() {
        return includeTax;
    }

    public void setIncludeTax(boolean includeTax) {
        this.includeTax = includeTax;
    }
}
