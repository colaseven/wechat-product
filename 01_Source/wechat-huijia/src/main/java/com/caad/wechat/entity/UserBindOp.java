package com.caad.wechat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "UserBindOp")
public class UserBindOp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6190857091774823250L;

    @Id
    private String openid;

    @Column(length = 50)
    private String loginname;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    private Date createtime;//回复的时间

    @Column(length = 20)
    private String status;

    @Column(length = 2000)
    private String removeddate;

    public String getRemoveddate() {
        return removeddate;
    }

    public void setRemoveddate(String removeddate) {
        this.removeddate = removeddate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
