package com.caad.wechat.entity;

import java.io.Serializable;
import java.util.List;

public class RedPackSendResp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8079149331393594421L;

    private String status;
    private String message;
    private List<RedPackSendRespItem> resultList;

    public RedPackSendResp(String status, String message, List<RedPackSendRespItem> resultList) {
        this.status = status;
        this.message = message;
        this.resultList = resultList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RedPackSendRespItem> getResultList() {
        return resultList;
    }

    public void setResultList(List<RedPackSendRespItem> resultList) {
        this.resultList = resultList;
    }

}
