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
@Table(name = "RedPackSendReq")
public class RedPackSendReq implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4935799268295808125L;

    @Id
    @GeneratedValue
    private long id;// 主键

    @Column(length = 50)
    private String loginname;// 用户名

    @Column(length = 50)
    private String opendid;//微信用户

    @Column(length = 10)
    private String totalamount;// 发放总金额

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;// 请求时间

    @Column(columnDefinition = "TEXT")
    private String encryptstr;//加密串

    @Column(columnDefinition = "TEXT")
    private String decryptstr;//解密串

    @Column(columnDefinition = "TEXT")
    private String responsestr;//返回数据

    public String getEncryptstr() {
        return encryptstr;
    }

    public void setEncryptstr(String encryptstr) {
        this.encryptstr = encryptstr;
    }

    public String getDecryptstr() {
        return decryptstr;
    }

    public void setDecryptstr(String decryptstr) {
        this.decryptstr = decryptstr;
    }

    public String getResponsestr() {
        return responsestr;
    }

    public void setResponsestr(String responsestr) {
        this.responsestr = responsestr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpendid() {
        return opendid;
    }

    public void setOpendid(String opendid) {
        this.opendid = opendid;
    }
}
