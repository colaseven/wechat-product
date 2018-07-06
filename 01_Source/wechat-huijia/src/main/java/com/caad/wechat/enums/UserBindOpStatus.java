package com.caad.wechat.enums;

public enum UserBindOpStatus {

    CreateAndDone("0", "创建并完成"), //
    CreateWithDuplicatedOpenid("1", "创建并发现微信重复"), //
    CreateWithDuplicatedLoginname("2", "创建并发现用户重复"), //
    DoneWithDuplicatedLoginname("3", "完成并移除重复用户"), //
    DoneWithDuplicatedOpenid("4", "完成并移除重复微信"); //

    public String flag;//状态的数字
    public String cname;// 中文字段名称

    UserBindOpStatus(String flag, String cname) {
        this.flag = flag;
        this.cname = cname;
    }

}
