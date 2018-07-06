package com.caad.wechat.utils.wechat;

import com.caad.wechat.api.WechatConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component("authenticationUtil")
public class AuthorizationUtil {

    private static Log log = LogFactory.getLog(AuthorizationUtil.class);

    @Autowired
    private WechatUtil wechatUtil;

    /**
     * 根据授权认证拿到code获取当前用户的openid
     *
     * @param code      授权后Code
     * @param appId     开发者Id
     * @param appSecret 开发者密码
     * @return 微信用户Id
     */
    public  String getOpenId(String code, String appId, String appSecret) {
        String openId = "";
        String url = WechatConstant.AUTHORIZATION_OPENID_URL.replace("APPID", appId).replace("APPSECRET", appSecret).replace("CODE",code);
        JSONObject jsonObject = this.wechatUtil.httpRequest(url, "GET", null);
        log.info("[authenticationUtil]：根据Code和AccessToken获取当前用户的openid:" + jsonObject);
        if (null == jsonObject || jsonObject.containsKey("errcode")) {
            log.info(String.format("[authenticationUtil]：授权获取当前用户openid错误:errcode:{%s} errmsg:{%s}", jsonObject.getString("errcode"), jsonObject.getString("errmsg")));
        } else {
            openId = jsonObject.getString("openid");
            log.info("授权获取当前用户openid：" + openId);
        }
        return openId;
    }
}
