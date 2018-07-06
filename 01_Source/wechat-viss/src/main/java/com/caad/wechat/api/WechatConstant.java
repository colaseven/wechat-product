package com.caad.wechat.api;

public class WechatConstant {


    /**
     * 授权页面
     */
    public static String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";


    /**
     * 获取access_token的接口地址（GET）
     */
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    /**
     * 获取微信用户信息（GET）
     */
    public static String WECHAT_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     * 支付请求获取统一订单号（POST）
     */
    public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 模板发送（POST）
     */
    public static String PUSH_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";


    /**
     * 授权获取openId (GET)
     */
    public static String AUTHORIZATION_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";


    /**
     * 查询订单地址
     */
    public static final String QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

}
