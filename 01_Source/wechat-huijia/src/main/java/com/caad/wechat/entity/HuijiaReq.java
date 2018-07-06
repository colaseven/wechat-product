package com.caad.wechat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "HuijiaReq")
public class HuijiaReq implements Serializable {

    private static final long serialVersionUID = -2169435265888204602L;

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 100)
    private String openid;

    @Column(length = 50)
    private String loginname;

    @Column(length = 50)
    private String vissid;

    @Column(length = 50)
    private String miniid;

    @Column(length = 50)
    private String assessTotalPrice;

    @Column(length = 50)
    private String assessSinglePrice;

    @Column(length = 6)
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date successtime;

    //	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	private Date deadlinetime;
    @Column(length = 20)
    private String deadlinetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date assesstime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submittime;

    @Column(columnDefinition = "TEXT")
    private String json;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getVissid() {
        return vissid;
    }

    public void setVissid(String vissid) {
        this.vissid = vissid;
    }

    public String getMiniid() {
        return miniid;
    }

    public void setMiniid(String miniid) {
        this.miniid = miniid;
    }

    public String getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(String assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public String getAssessSinglePrice() {
        return assessSinglePrice;
    }

    public void setAssessSinglePrice(String assessSinglePrice) {
        this.assessSinglePrice = assessSinglePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getAssesstime() {
        return assesstime;
    }

    public void setAssesstime(Date assesstime) {
        this.assesstime = assesstime;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

//	public Date getDeadlinetime() {
//		return deadlinetime;
//	}
//
//	public void setDeadlinetime(Date deadlinetime) {
//		this.deadlinetime = deadlinetime;
//	}


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(String deadlinetime) {
        this.deadlinetime = deadlinetime;
    }

    public Date getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(Date successtime) {
        this.successtime = successtime;
    }

}
