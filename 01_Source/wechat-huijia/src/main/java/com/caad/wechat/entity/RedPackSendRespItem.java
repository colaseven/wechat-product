package com.caad.wechat.entity;

import java.io.Serializable;

public class RedPackSendRespItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3893981940313909313L;

    private String loginName;// 用户名
    private String tipId;// 红包id
    private String amounts;// 金额
    private String returnCode;//发送结果代码-SUCCESS/FAIL
    private String returnMsg;// 发送结果说明
    private String errCode;// 错误代码
    private String errMsg;// 错误说明
    private String sendTime;// 发送时间
    private String status;//发送结果

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTipId() {
        return tipId;
    }

    public void setTipId(String tipId) {
        this.tipId = tipId;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
