package com.caad.wechat.controller;

import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.model.viss.common.CompanyDetail;
import com.caad.wechat.model.viss.common.OrganizaionAndAreaResult;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.QueryAreaModel;
import com.caad.wechat.model.viss.common.SystemResidueCountResult;
import com.caad.wechat.model.viss.common.SystemResultTabShow;
import com.caad.wechat.model.viss.common.WeChatCustomField;
import com.caad.wechat.service.wechat.ICommonService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.PhonePatternUtil;
import com.caad.wechat.utils.viss.StringUtils;
import com.caad.wechat.utils.wechat.AuthorizationUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

@RequestMapping("/home")
@Controller
public class HomeController {

    private static Log log = LogFactory.getLog(HomeController.class);

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
        String requestURI = VISS_BASEURL + "home/index.do";
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
        return "redirect:/view/index.html";

    }


    /**
     * 首页数据初始化
     *
     * @param request request
     * @return map
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public OrganizaionAndAreaResult getOrganizationInfo(HttpServletRequest request) {

        OrganizaionAndAreaResult organizaionAndAreaResult = new OrganizaionAndAreaResult();
        try {
            String openId = (String) request.getSession().getAttribute("openId");
            log.info("首页info方法获取缓存中的openId：" + openId);
            String appId = (String) request.getSession().getAttribute("appId");
            log.info("首页info方法获取缓存中的appId：" + appId);
            OrganizationWeChatSettingResult organizationInfo = commonUtil.getOrganizationBySession(request, appId);
            List<WeChatCustomField> fields = organizationInfo.getFields();
            String statement = "";
            if (fields.size() > 0) {
                for (WeChatCustomField field : fields) {
                    if (field.getName().equals("免责声明")) {
                        statement = PhonePatternUtil.phoneNumberPattern(field.getValue());
                    }
                }
            }
            log.debug("[home]：获取机构信息：" + JSONObject.fromObject(organizationInfo));
            organizaionAndAreaResult.setOrganizationInfo(organizationInfo);
            //获取查询区域
            String data = JSONObject.fromObject(commonSerice.getPropertyAndArea()).toString();
            QueryAreaModel areaModel = com.alibaba.fastjson.JSONObject.parseObject(data, QueryAreaModel.class);
            organizaionAndAreaResult.setArea(areaModel);
            organizaionAndAreaResult.setOpenId(openId);
            organizaionAndAreaResult.setStatement(statement);
        } catch (Exception e) {
            organizaionAndAreaResult.setErrorMessage(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
        }
        return organizaionAndAreaResult;
    }


    /**
     * 获取系统询价使用次数
     */
    @RequestMapping(value = "/systemResidueCount", method = RequestMethod.GET)
    @ResponseBody
    public SystemResidueCountResult getSystemAssessmentUserResidueCount(HttpServletRequest request,
                                                                        @RequestParam(value = "propertyId") long propertyId) {
        SystemResidueCountResult systemResidueCountResult = new SystemResidueCountResult();
        //系统询价次数
        try {
            String appId = (String) request.getSession().getAttribute("appId");
            String openId = (String) request.getSession().getAttribute("openId");
            long systemAssessmentUserResidueCount = commonSerice.getSystemAssessmentUserResidueCount(appId, openId, propertyId);
            log.info("[home]：获取系统询价次数：" + systemAssessmentUserResidueCount);
            systemResidueCountResult.setResidueCount(systemAssessmentUserResidueCount);
        } catch (Exception e) {
            systemResidueCountResult.setMessage(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
        }
        return systemResidueCountResult;
    }


    /**
     * 获取用户快捷回价使用次数
     */
    @RequestMapping(value = "/userResidueCount", method = RequestMethod.GET)
    @ResponseBody
    public long getUserResidueCount(HttpServletRequest request,
                                    @RequestParam(value = "productId") long productId) {
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        long userResidueCount = commonSerice.getUserResidueCount(appId, openId, productId);
        log.info("[home]：获取快捷回价使用次数：" + userResidueCount);
        return userResidueCount;
    }


    /**
     * 获取公司简介详情
     */
    @RequestMapping(value = "/companyDetail", method = RequestMethod.GET)
    @ResponseBody
    public String companyDetail(HttpServletRequest request) throws UnsupportedEncodingException {
        String appId = (String) request.getSession().getAttribute("appId");
        CompanyDetail companyProfile = this.commonSerice.getCompanyProfile(appId);
        return companyProfile.getCompanyProfile();
    }


    /**
     * 系统询价接口界面是否显示小区信息、小区图片tab
     */
    @RequestMapping(value = "/isTabShow", method = RequestMethod.GET)
    @ResponseBody
    public SystemResultTabShow isTabShow(HttpServletRequest request) {
        String appId = (String) request.getSession().getAttribute("appId");
        return this.commonSerice.getResult(appId);
    }


    /**
     * 清除session
     */
    @RequestMapping(value = "/cleanSession", method = RequestMethod.GET)
    @ResponseBody
    public String cleanSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "清除缓存成功";
    }
}
