package com.caad.wechat.entity;

import java.io.Serializable;

public class RedPackQueryRespItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7854481763315291171L;

    private String tipId;

    private String status;


    public String getTipId() {
        return tipId;
    }

    public void setTipId(String tipId) {
        this.tipId = tipId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
