package com.caad.wechat.model.wechat;

import java.util.HashMap;
import java.util.Map;

public class WxTemplate {

    /**
     * 模板消息id
     */
    private String template_id;
    /**
     * 用户openId
     */
    private String touser;
    /**
     * URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
     */
    private String url;
    /**
     * 标题颜色
     */
    private String topcolor;
    /**
     * 详细内容
     */
    private Map<String, WxTemplateData> data;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public void setData(Map<String, WxTemplateData> map) {
        this.data = map;
    }

    public Map<String, WxTemplateData> getData() {
        return data;
    }

    public void addDataItem(String key, String color, String text) {
        if (this.data == null) this.data = new HashMap<String, WxTemplateData>();
        //
        WxTemplateData data = new WxTemplateData(text, color);
        this.data.put(key, data);
    }

}