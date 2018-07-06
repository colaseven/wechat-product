package com.caad.wechat.controller;

import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.wechat.WxTemplate;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.wechat.WechatUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 微信模板推送消息
 */
@RequestMapping("/push")
@Controller
public class PushController {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private WechatUtil wechatUtil;

    private static Log log = LogFactory.getLog(PushController.class);


    /**
     * 模板消息推送及状态推送
     *
     * @param json    推送信息
     * @param request request
     */
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    @ResponseBody
    public String notice(@RequestBody JSONObject json, HttpServletRequest request) {
        log.info("[push]：获取模板消息数据" + String.valueOf(json));
        String appId = json.getString("appId");
        request.getSession().setAttribute("appId", appId);
        WxTemplate wxtemplate = new WxTemplate();
        wxtemplate.setUrl(json.getString("url"));
        wxtemplate.setTouser(json.getString("openId"));
        wxtemplate.setTopcolor("#173177");
        wxtemplate.setTemplate_id(json.getString("template_id"));
        wxtemplate.addDataItem("first", "#173177", json.getString("title"));
        wxtemplate.addDataItem("keyword1", "#173177", json.getString("keyword1"));
        wxtemplate.addDataItem("keyword2", "#173177", json.getString("keyword2"));
        if (json.containsKey("keyword3")) {
            wxtemplate.addDataItem("keyword3", "#173177", json.getString("keyword3"));
            wxtemplate.addDataItem("keyword4", "#173177", json.getString("keyword4"));
        }
        wxtemplate.addDataItem("remark", "#173177", json.getString("remark"));
        String jsonString = JSONObject.fromObject(wxtemplate).toString();
        OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, appId);
        String access_token = this.wechatUtil.getAccessToken(organizationBySession.getAppId(), organizationBySession.getSecret());
        //String access_token="6_AyfHwfWQCe2KIbrbd3dL_f92qw7yl8UqrR4LvuTWb1gjYGEh3Whs6WWwBTznr2j036aJaIt0_kuaQATvu-cAh5_X3_WfYlAp298RWDa1u4FVZ8QsPwyq7GOWaxkZDPhAGAQUG";
        String url = WechatConstant.PUSH_TEMPLATE_URL + access_token;
        log.info("[push]：模板消息发送中" + url);
        JSONObject jsonObject = wechatUtil.httpRequest(url, "POST", jsonString);
        int result;
        if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            log.error(String.format("[push]：模板消息发送异常:errcode:{%s} errmsg:{%s}", result, jsonObject.getString("errmsg")));
            return "FAIL";
        } else {
            log.info("[push]：模板消息发送完成：" + jsonObject);
            return "SUCCESS";
        }
    }
}
