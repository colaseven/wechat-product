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
@Table(name = "RedPackQueryReq")
public class RedPackQueryReq implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3879506961365974759L;

    @Id
    @GeneratedValue
    private long id;// 主键

    @Column(length = 50)
    private String mchbillno;// 红包订单号

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;// 查询时间

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

    public String getMchbillno() {
        return mchbillno;
    }

    public void setMchbillno(String mchbillno) {
        this.mchbillno = mchbillno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
