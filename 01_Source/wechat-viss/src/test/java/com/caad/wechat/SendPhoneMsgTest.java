package com.caad.wechat;


import com.caad.wechat.utils.viss.SendShortMessageUtil;

public class SendPhoneMsgTest {

    public static void main(String[] args){
        try{
            String text = "您的验证码为：123456，2分钟内有效。如有疑问请咨询热线 021-62901818。温馨提示:如非本人操作,请忽略此短信!";
            SendShortMessageUtil.sendShortMessage("15221312712",text,"中估联");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
