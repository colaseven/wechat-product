package com.caad.wechat.entity;

public class RedPackQueryResp extends RedPackQueryRespItem {

    /**
     *
     */
    private static final long serialVersionUID = 7836272752697390176L;

    private String status;

    private String message;

    private RedPackQueryRespItem result;

    public RedPackQueryResp(String status, String message, RedPackQueryRespItem result) {
        this.status = status;
        this.message = message;
        this.result = result;
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

    public RedPackQueryRespItem getResult() {
        return result;
    }

    public void setResult(RedPackQueryRespItem result) {
        this.result = result;
    }


}
