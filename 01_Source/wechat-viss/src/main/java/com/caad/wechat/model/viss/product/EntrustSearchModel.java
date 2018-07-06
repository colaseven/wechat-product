package com.caad.wechat.model.viss.product;


public class EntrustSearchModel {

    /**
     * 微信用户openId
     */
    private String userOpenId;

    private String weChatComCondition;

    private String entrustType;

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getWeChatComCondition() {
        return weChatComCondition;
    }

    public void setWeChatComCondition(String weChatComCondition) {
        this.weChatComCondition = weChatComCondition;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }
}
