package com.caad.wechat.enums;

public enum RedPackStatus {


    // 查询红包返回的红包状态
    Sending("SENDING", "发放中 ", "0"),

    Sent("SENT", "已发放待领取", "1"),

    Failed("FAILED", "发放失败", "2"),

    Received("RECEIVED", "已领取", "3"),

    Refund("REFUND", "已退款", "4");


    public String ename; // 对应红包返回的状态
    public String cname;// 对应红包状态的中文字段名称
    public String flag;//对应红包状态的数字


    RedPackStatus(String ename, String cname, String flag) {
        this.ename = ename;
        this.cname = cname;
        this.flag = flag;
    }

    public static RedPackStatus getByEname(String n) {
        for (RedPackStatus e : values()) {
            if (e.ename.equals(n)) return e;
        }
        return null;
    }
}
