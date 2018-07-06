package com.caad.wechat.utils.sdk;

import org.codehaus.jackson.annotate.JsonProperty;

public class ApiMessage<T> {

    public ApiMessage() {
    }

    @JsonProperty("State")
    private int state;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("ErrorCode")
    private String errorCode;
    @JsonProperty("Data")
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ApiMessage(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
