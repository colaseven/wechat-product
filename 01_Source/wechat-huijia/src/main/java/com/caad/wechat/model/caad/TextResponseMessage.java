package com.caad.wechat.model.caad;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文本消息
 */
@XStreamAlias("xml")
public class TextResponseMessage extends ResponseMessage implements Serializable {

    private static final long serialVersionUID = 6444584891550992294L;

    private String Content;// 回复的消息内容

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}