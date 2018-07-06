package com.caad.wechat.enums;

public enum HuijiaReqStatus {

    Create("1", "创建"), //
    assess("2", "估价"), //
    Submit("3", "提交"), //
    Message("4", "通知"); //

    public String flag;//状态的数字
    public String cname;// 中文字段名称

    HuijiaReqStatus(String flag, String cname) {
        this.flag = flag;
        this.cname = cname;
    }

}
