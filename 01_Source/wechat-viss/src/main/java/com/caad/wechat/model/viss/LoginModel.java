package com.caad.wechat.model.viss;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoginModel {


    @JsonProperty("UserName")
    private String userName ;

    @JsonProperty("Password")
    private String passWord ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
