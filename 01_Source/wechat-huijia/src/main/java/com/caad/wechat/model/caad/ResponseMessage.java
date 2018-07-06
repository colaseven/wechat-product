package com.caad.wechat.model.caad;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 消息基类（公众帐号 -> 普通用户）
 */
@XStreamAlias("xml")
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = -5990589357601735816L;

    private @XStreamAlias("ToUserName")
    String ToUserName;// 接收方帐号（收到的OpenID）

    private @XStreamAlias("FromUserName")
    String FromUserName;// 开发者微信号

    private @XStreamAlias("CreateTime")
    long CreateTime;// 消息创建时间 （整型）

    private @XStreamAlias("MsgType")
    String MsgType;// 消息类型（text/music/news）

    private @XStreamAlias("MsgId")
    long MsgId;// 消息id，64位整型

    private @XStreamAlias("Title")
    String Title;// 消息标题

    private @XStreamAlias("Description")
    String Description;// 消息描述

    private @XStreamAlias("Url")
    String Url;// 消息链接

    private @XStreamAlias("FuncFlag")
    int FuncFlag;

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    /**
     * @return the toUserName
     */
    public String getToUserName() {
        return ToUserName;
    }

    /**
     * @param toUserName the toUserName to set
     */
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    /**
     * @return the fromUserName
     */
    public String getFromUserName() {
        return FromUserName;
    }

    /**
     * @param fromUserName the fromUserName to set
     */
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    /**
     * @return the createTime
     */
    public long getCreateTime() {
        return CreateTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    /**
     * @return the msgType
     */
    public String getMsgType() {
        return MsgType;
    }

    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
