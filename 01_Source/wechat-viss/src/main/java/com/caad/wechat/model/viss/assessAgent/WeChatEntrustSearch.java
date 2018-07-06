package com.caad.wechat.model.viss.assessAgent;


public class WeChatEntrustSearch {


    private int pageIndex;


    private int pageSize;


    private String userOpenId;


    private String entrustType;


    private String weChatComCondition;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public String getWeChatComCondition() {
        return weChatComCondition;
    }

    public void setWeChatComCondition(String weChatComCondition) {
        this.weChatComCondition = weChatComCondition;
    }
}
