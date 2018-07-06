package com.caad.wechat.model.wechat;

public class ResultTemplateEntity {

    private String result;
    private String reason;

    public ResultTemplateEntity() {

    }

    public ResultTemplateEntity(String result, String reason, String json) {
        this.result = result;
        this.reason = reason;
    }

    public ResultTemplateEntity(String result, String reason) {
        this.result = result;
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
