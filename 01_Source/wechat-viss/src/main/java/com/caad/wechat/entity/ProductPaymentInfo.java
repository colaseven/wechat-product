package com.caad.wechat.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_productPaymentInfo")
public class ProductPaymentInfo implements Serializable {

    private static final long serialVersionUID = -2169489255888207892L;

    @Id
    private String boughtNumber;//商户订单号

    @Column(length = 100)
    private String openid;

    @Column(length = 100)
    private String appid;

    @Column(length = 100)
    private String count;//次数

    @Column(length = 100)
    private String total_fee;//订单总金额，单位为分

    @Column(length = 100)
    private String productId;//产品Id

    @Column(length = 100)
    private String productName;//产品名称

    @Column(length = 100)
    private String body;//商品描述

    @Column(length = 100)
    private String time; //支付时间

    @Column(length = 100)
    private String isNotifySuccess;


    public String getBoughtNumber() {
        return boughtNumber;
    }

    public void setBoughtNumber(String boughtNumber) {
        this.boughtNumber = boughtNumber;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsNotifySuccess() {
        return isNotifySuccess;
    }

    public void setIsNotifySuccess(String isNotifySuccess) {
        this.isNotifySuccess = isNotifySuccess;
    }
}
