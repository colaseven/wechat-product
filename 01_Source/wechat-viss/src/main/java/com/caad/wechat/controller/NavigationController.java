package com.caad.wechat.controller;


import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.service.wechat.ICommonService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.StringUtils;
import com.caad.wechat.utils.wechat.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/navigation")
@Controller
public class NavigationController {

    /**
     * 微信正式环境访问地址
     */
    @Value("#{settings['viss.baseurl']}")
    private String VISS_BASEURL;

    /**
     * 微信授权工具类
     */
    @Autowired
    private AuthorizationUtil authorizationUtil;

    /**
     * 公共方法 获取session
     */
    @Autowired
    private CommonUtil commonUtil;

    /**
     * 接口注入
     */
    @Autowired
    @Resource(name = "commonSerice")
    private ICommonService commonSerice;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, @RequestParam(value = "appId", defaultValue = "") String newAppId) {
        String sessionAppId = (String) request.getSession().getAttribute("appId");
        String appId = StringUtils.isNotEmptyOrNull(newAppId) ? newAppId : (StringUtils.isNotEmptyOrNull(sessionAppId) ? sessionAppId : "");//判断入口是否是第一次进来
        String requestURI = VISS_BASEURL + "navigation/index.do";
        return "redirect:" + WechatConstant.AUTHORIZATION_URL.replace("APPID", appId).replace("REDIRECT_URI", requestURI).replace("STATE", appId);
    }


    /**
     * 用户授权跳转该页面并获取自定义参数“appId“ 并获取授权code;
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        String appId = request.getParameter("state");
        //String appId = "wxe643becc64502702";//TODO
        request.getSession().setAttribute("appId", appId);
        //获取机构信息并存放在缓存中
        OrganizationWeChatSettingResult organizationInfo = commonUtil.getOrganizationBySession(request, appId);
        //根据code获取微信openId
        //String openId = "oh9Bzv-9H8cCkrTVbkS2zcCOAeL4";//oMxrat1tlIC_Ehcfecdb6sSgtkw0--->wg   oh9Bzv7_coMVgmwDILldizv0gmjk--->swq TODO
        String openId = this.authorizationUtil.getOpenId(code, appId, organizationInfo.getSecret());//用户授权的openid
        commonSerice.addProductFreeUse(appId, openId); //增加询价次数接口
        request.getSession().setAttribute("openId", openId);
        return "redirect:/view/navigation.html";
    }
}
