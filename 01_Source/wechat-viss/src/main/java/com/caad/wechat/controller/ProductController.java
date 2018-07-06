package com.caad.wechat.controller;


import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.PaymentResult;
import com.abc.pay.client.ebus.UnifiedPaymentRequest;
import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.dao.ProductPaymentInfoDao;
import com.caad.wechat.entity.ProductPaymentInfo;
import com.caad.wechat.model.viss.common.BindResultModel;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.QueryAreaModel;
import com.caad.wechat.model.viss.common.WeChatUserBindParam;
import com.caad.wechat.model.viss.product.ProductBoughtHistoryModel;
import com.caad.wechat.model.viss.product.ProductBoughtModel;
import com.caad.wechat.model.viss.product.SysProductResult;
import com.caad.wechat.model.viss.product.WeChatProductTypeModel;
import com.caad.wechat.service.wechat.ICommonService;
import com.caad.wechat.service.wechat.IProductService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.DateUtil;
import com.caad.wechat.utils.viss.SendShortMessageUtil;
import com.caad.wechat.utils.viss.StringUtils;
import com.caad.wechat.utils.wechat.AuthorizationUtil;
import com.caad.wechat.utils.wechat.WechatPayUtil;
import com.caad.wechat.utils.wechat.WechatUtil;
import net.sf.json.JSONArray;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 微信支付
 */
@RequestMapping("/product")
@Controller
public class ProductController {

    private static Log log = LogFactory.getLog(ProductController.class);

    @Autowired
    private WechatPayUtil wechatPayUtil;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Autowired
    private ProductPaymentInfoDao productPaymentInfoDao;

    private final static int CODE_LENGTH = 6; // 验证码长度

    // 验证码过期时间，秒
    private final static float LIMIT_TIME = 120.0f;

    /**
     * 微信正式环境访问地址
     */
    @Value("#{settings['viss.baseurl']}")
    private String VISS_BASEURL;

    /**
     * 微信工具类
     */
    @Autowired
    private WechatUtil wechatUtil;

    /**
     * 接口注入
     */
    @Autowired
    @Resource(name = "commonSerice")
    private ICommonService commonSerice;

    /**
     * 接口注入
     */
    @Autowired
    @Resource(name = "productService")
    private IProductService productService;


    /**
     * 支付入口
     */
    @RequestMapping("/payInit")
    public String payInit(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String requestURI = VISS_BASEURL + "product/payIndex.do";
        return "redirect:" + WechatConstant.AUTHORIZATION_URL.replace("APPID", appId).replace("REDIRECT_URI", requestURI).replace("STATE", appId);
    }

    /**
     * 支付入口
     */
    @RequestMapping("/payIndex")
    public String payIndex(HttpServletRequest request) {
        String appId = request.getParameter("state");
        String code = request.getParameter("code");
        request.getSession().setAttribute("appId", appId);
        OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, appId);
        //根据code获取微信openId
        String openId = this.authorizationUtil.getOpenId(code, appId, organizationBySession.getSecret());//用户授权的openid
        request.getSession().setAttribute("openId", openId);
        return "redirect:/view/purchaseList.html";
    }

    /**
     * 获取支付界面初始化页面logo
     */
    @RequestMapping("/getLoginImgUrl")
    @ResponseBody
    public String getLoginImgUrl(HttpServletRequest request) {
        OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, (String) request.getSession().getAttribute("appId"));
        return organizationBySession.getLogoImgUrl();
    }


    /**
     * 获取物业类型
     */
    @RequestMapping(value = "/propertTypeList", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray propertTypeList() {
        QueryAreaModel areaModel = commonSerice.getPropertyAndArea();
        JSONArray jsonArray = JSONArray.fromObject(areaModel.getPropertyTypes());
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonArray.getJSONObject(i).remove("areas");
        }
        return jsonArray;
    }


    /**
     * 根据物业类型获取快捷回价列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<SysProductResult> getQuickProductList(@RequestParam(value = "propertyId") long propertyId,
                                                      @RequestParam(value = "templateTypeId") String templateTypeId,
                                                      HttpServletRequest request) {
        List<SysProductResult> list = new ArrayList<>();
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        JSONArray jsonArray = JSONArray.fromObject(this.productService.getProductList(propertyId, appId, openId, templateTypeId));
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), SysProductResult.class));
        }
        return list;
    }


    /**
     * 购买列表
     */
    @RequestMapping("/paymentList")
    @ResponseBody
    public List<WeChatProductTypeModel> getProductList(HttpServletRequest request, @RequestParam(value = "propertyId") long propertyId) {
        List<WeChatProductTypeModel> list = new ArrayList<>();
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        JSONArray jsonArray = JSONArray.fromObject(this.productService.getProductBoughtList(openId, appId, propertyId));
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), WeChatProductTypeModel.class));
        }
        return list;
    }


    /**
     * 处理支付请求参数封装、校验并调用微信SDK付款
     */
    @RequestMapping(value = "/payment", method = {RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings("unchecked")
    public JSONObject payment(HttpServletRequest request,
                              @RequestParam(value = "count") String count,//次数
                              @RequestParam(value = "price") String price,//价格
                              @RequestParam(value = "productId") String productId,//产品Id
                              @RequestParam(value = "productName") String productName//产品名称
    ) {

        String orderNo = System.currentTimeMillis() + WechatPayUtil.genRandomStr(10);//当前订单号
        //从session中获取 商户Id 商户秘钥等
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
        String body = productName + "：" + price + "/元" + count + "次";
        String mchId = organizationInfo.getBusinessId();//商户Id
        String mchKey = organizationInfo.getPayPassWord();//商户秘钥
        try {
            JSON abcResult = null;
            Map<String, String> paymentResultInfo = new HashMap<>();
            if (appId.equals("wx4274ce8a60e68b5d")) {
                //在支付回调成功后向农行发起请求
                UnifiedPaymentRequest param = new UnifiedPaymentRequest();
                //设置订单的属性
                param.dicOrder.put("PayTypeID", "JSAPI");
                param.dicOrder.put("OrderDate", DateUtil.getDate("yyyy/MM/dd", new Date()));
                param.dicOrder.put("OrderTime", DateUtil.getDate("HH:mm:ss", new Date()));
                param.dicOrder.put("CurrencyCode", "156");//交易币种 156人民币
                param.dicOrder.put("OrderNo", orderNo);
                param.dicOrder.put("OrderAmount", price);
                param.dicOrder.put("AccountNo", appId);//
                param.dicOrder.put("OpenID", openId);
                param.dicOrder.put("InstallmentMark", "0");
                param.dicOrder.put("CommodityType", "0499");
                param.dicOrder.put("OrderDesc", "厦门中利评估-产品购买");
                //将订单明细加入订单中
                LinkedHashMap orderDetail = new LinkedHashMap();
                orderDetail.put("ProductName", productName);
                param.orderitems.put("orderDetail", orderDetail);
                //设定支付请求对象的属性
                param.dicRequest.put("PaymentType", "8");
                param.dicRequest.put("PaymentLinkType", "2");
                param.dicRequest.put("NotifyType", "1");
                param.dicRequest.put("ResultNotifyURL", VISS_BASEURL + "product/abcNotify.do");
                param.dicRequest.put("IsBreakAccount", "0");
                abcResult = param.postRequest();
            } else {
                log.info("[product]：获取支付商户Id：" + mchId);
                log.info("[product]：获取支付商户秘钥：" + mchKey);
                Map<String, String> params = new HashMap<>();
                params.put("appid", appId);
                params.put("body", productName + "：" + price + "/元" + count + "次");//商品描述
                params.put("device_info", "WEB");//设备号
                params.put("mch_id", mchId);// 商户号
                params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
                params.put("notify_url", VISS_BASEURL + "product/notify.do");//服务器异步通知页面路径
                params.put("openid", openId);
                params.put("out_trade_no", orderNo);//商户订单号
                params.put("total_fee", String.valueOf(Math.round((Double.parseDouble(price) * 100))));//总金额 ，单位为分
                params.put("trade_type", "JSAPI");//交易类型 SAPI--公众号支付
                params.put("spbill_create_ip", wechatPayUtil.getHostIp());
                params.put("sign", wechatPayUtil.getSign(params, mchKey));//支付秘钥
                log.debug("[product]：请求支付开始准备参数：" + JSONObject.fromObject(params));
                String httpResult = wechatPayUtil.postHttplient(WechatConstant.UNIFIED_ORDER_URL, wechatPayUtil.mapToXml(params));
                paymentResultInfo = wechatPayUtil.xmlToMap(httpResult);
                log.debug("[product]：调用支付接口获取到的数据：" + JSONObject.fromObject(paymentResultInfo));
                log.info("[product]：获取订单号：" + paymentResultInfo.get("prepay_id"));
                //保存数据库开始
            }
            ProductPaymentInfo productPaymentInfo = new ProductPaymentInfo();
            productPaymentInfo.setAppid(appId);
            if (appId.equals("wx4274ce8a60e68b5d")) {
                productPaymentInfo.setBody(body + "-农行支付");
            } else {
                productPaymentInfo.setBody(body + "-微信支付");
            }
            productPaymentInfo.setCount(count);
            productPaymentInfo.setOpenid(openId);
            productPaymentInfo.setBoughtNumber(orderNo);
            productPaymentInfo.setProductId(productId);
            productPaymentInfo.setProductName(productName);
            productPaymentInfo.setTotal_fee(price);
            productPaymentInfo.setTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
            productPaymentInfo.setIsNotifySuccess("start");
            productPaymentInfoDao.save(productPaymentInfo);
            //保存数据库结束
            Map<String, String> result = new HashMap<>();
            if (appId.equals("wx4274ce8a60e68b5d")) {
                result.put("appId", abcResult.GetKeyValue("sub_appId"));
                result.put("timeStamp", abcResult.GetKeyValue("timeStamp"));
                result.put("nonceStr", abcResult.GetKeyValue("nonceStr"));
                result.put("package", abcResult.GetKeyValue("package"));
                result.put("signType", abcResult.GetKeyValue("signType"));
                result.put("paySign", abcResult.GetKeyValue("paySign"));
                log.info("[product]：农行支付回调返回成功页面数据=" + JSONObject.fromObject(result));
                return JSONObject.fromObject(result);
            } else {
                result.put("appId", appId);
                result.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
                result.put("nonceStr", paymentResultInfo.get("nonce_str"));
                result.put("package", "prepay_id=" + paymentResultInfo.get("prepay_id"));
                result.put("signType", "MD5");
                result.put("paySign", wechatPayUtil.getSign(result, mchKey));
                log.debug("[product]：支付回调返回成功页面数据=" + JSONObject.fromObject(result));
                return JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 支付回调
     */
    @RequestMapping(value = "notify")
    @ResponseBody
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader reader;
        reader = request.getReader();
        String line;
        String xmlString;
        StringBuffer inputString = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        xmlString = inputString.toString();
        request.getReader().close();
        Map<String, String> map = wechatPayUtil.xmlToMap(xmlString);
        log.info("[product]：支付回调获取到支付信息：" + JSONObject.fromObject(map));
        request.getSession().setAttribute("appId", map.get("appid"));
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, map.get("appid"));
        ProductPaymentInfo productPaymentInfo = productPaymentInfoDao.findByBoughtNumber(map.get("out_trade_no"));
        boolean notifyBoolean = productPaymentInfo.getIsNotifySuccess().equals("start") || productPaymentInfo.getIsNotifySuccess().equals("false");
        log.info("[product]：支付回调判断：" + notifyBoolean);
        String return_code = "";
        if (map.get("return_code").equals("SUCCESS") && notifyBoolean) {
            //将支付接口信息进行回调
            log.info("[product]：支付回调开始：" + JSONObject.fromObject(productPaymentInfo));
            try {
                ProductBoughtModel param = new ProductBoughtModel();
                param.setBoughtNumber(productPaymentInfo.getBoughtNumber());//订单号
                param.setTime(productPaymentInfo.getTime());//支付时间
                param.setProductId(productPaymentInfo.getProductId());//产品Id
                param.setProductTypeName(productPaymentInfo.getProductName());//产品名称
                param.setCount(productPaymentInfo.getCount());//次数
                param.setPrice(productPaymentInfo.getTotal_fee());//价格
                param.setModeOfPayment("微信支付");//支付方式
                param.setUserOpenId(productPaymentInfo.getOpenid()); //openId
                JSONObject userInfo = wechatUtil.getWechatUserInfo(this.wechatUtil.getAccessToken(organizationInfo.getAppId(), organizationInfo.getSecret()), productPaymentInfo.getOpenid());
                param.setUserName(userInfo.getString("nickname"));////微信昵称
                param.setOrganizationId(organizationInfo.getOrganizationId());//机构id
                param.setOrganizationName(organizationInfo.getOrganizationName());//机构名称
                param.setCompanyId(productPaymentInfo.getAppid());//appId
                param.setCompanyName(organizationInfo.getAppName());//微信公众号名称
                param.setWeChatPayInfo(JSONObject.fromObject(map));
                notifyBoolean = this.productService.productBought(param);
                log.info("[product]：支付回调成功数据：" + JSONObject.fromObject(param));
                return_code = "SUCCESS";
            } catch (Exception e) {
                log.error("[product]：支付回调失败：" + e.getMessage());
                notifyBoolean = false;
                return_code = "FAIL";
            }
            productPaymentInfo.setIsNotifySuccess(String.valueOf(notifyBoolean));
        } else if (map.get("return_code").equals("FAIL")) {
            productPaymentInfo.setIsNotifySuccess("false");
            return_code = "FAIL";
        }
        productPaymentInfoDao.save(productPaymentInfo);
        response.getWriter().write("<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
    }


    /**
     * 支付回调
     */
    @RequestMapping(value = "abcNotify")
    @ResponseBody
    public void abcNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = request.getParameter("MSG");
        PaymentResult tResult = new PaymentResult(msg);
        Map<String, String> map = wechatPayUtil.xmlToMap(tResult.getResponseMessage());
        log.info("[农行支付获取回调数据:]" + JSONObject.fromObject(map).toString());
        ProductPaymentInfo productPaymentInfo = productPaymentInfoDao.findByBoughtNumber(map.get("OrderNo"));
        String return_code;
        if (tResult.isSuccess()) {
            boolean notifyBoolean = productPaymentInfo.getIsNotifySuccess().equals("start") || productPaymentInfo.getIsNotifySuccess().equals("false");
            String appId = productPaymentInfo.getAppid();
            request.getSession().setAttribute("appId", appId);
            OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
            if (notifyBoolean) {
                try {
                    ProductBoughtModel param = new ProductBoughtModel();
                    param.setBoughtNumber(productPaymentInfo.getBoughtNumber());//订单号
                    param.setTime(productPaymentInfo.getTime());//支付时间
                    param.setProductId(productPaymentInfo.getProductId());//产品Id
                    param.setProductTypeName(productPaymentInfo.getProductName());//产品名称
                    param.setCount(productPaymentInfo.getCount());//次数
                    param.setPrice(productPaymentInfo.getTotal_fee());//价格
                    param.setModeOfPayment("农行支付");//支付方式
                    param.setUserOpenId(productPaymentInfo.getOpenid()); //openId
                    JSONObject userInfo = wechatUtil.getWechatUserInfo(this.wechatUtil.getAccessToken(organizationInfo.getAppId(), organizationInfo.getSecret()), productPaymentInfo.getOpenid());
                    param.setUserName(userInfo.getString("nickname"));////微信昵称
                    param.setOrganizationId(organizationInfo.getOrganizationId());//机构id
                    param.setOrganizationName(organizationInfo.getOrganizationName());//机构名称
                    param.setCompanyId(productPaymentInfo.getAppid());//appId
                    param.setCompanyName(organizationInfo.getAppName());//微信公众号名称
                    param.setWeChatPayInfo(JSONObject.fromObject(map));
                    notifyBoolean = this.productService.productBought(param);
                    log.info("[product]：农行支付回调成功数据：" + JSONObject.fromObject(param));
                    return_code = "SUCCESS";
                } catch (Exception e) {
                    log.error("[product]：农行支付回调失败：" + e.getMessage());
                    notifyBoolean = false;
                    return_code = "FAIL";
                }
                productPaymentInfo.setIsNotifySuccess(String.valueOf(notifyBoolean));
            } else {
                productPaymentInfo.setIsNotifySuccess("false");
                return_code = "FAIL";
            }
        } else {
            productPaymentInfo.setIsNotifySuccess("false");
            return_code = "FAIL";
        }
        productPaymentInfoDao.save(productPaymentInfo);
        response.getWriter().write(return_code);
    }

    /**
     * 支付历史入口
     */
    @RequestMapping("/historyInit")
    public String historyInit(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String requestURI = VISS_BASEURL + "product/historyIndex.do";
        return "redirect:" + WechatConstant.AUTHORIZATION_URL.replace("APPID", appId).replace("REDIRECT_URI", requestURI).replace("STATE", appId);
    }

    /**
     * 支付历史入口
     */
    @RequestMapping("/historyIndex")
    public String historyIndex(HttpServletRequest request) {
        String appId = request.getParameter("state");
        String code = request.getParameter("code");
        request.getSession().setAttribute("appId", appId);
        OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, appId);
        //根据code获取微信openId
        String openId = this.authorizationUtil.getOpenId(code, appId, organizationBySession.getSecret());//用户授权的openid
        request.getSession().setAttribute("openId", openId);
        return "redirect:/view/purchaseHistory.html";
    }


    /**
     * 购买历史列表
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public ProductBoughtHistoryModel getBoughtHistory(HttpServletRequest request) {
        ProductBoughtHistoryModel boughtHistory = this.productService.getBoughtHistory((String) request.getSession().getAttribute("openId"));
        log.debug("[product]：获取购买历史列表数据：" + JSONObject.fromObject(boughtHistory));
        return boughtHistory;
    }


    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/sendSMS", method = RequestMethod.GET)
    @ResponseBody
    public void sendSMS(@RequestParam(value = "phoneNumber") String phoneNumber, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            String appId = (String) request.getSession().getAttribute("appId");
            OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
            //生成验证码
            String code = "";
            while (code.length() < CODE_LENGTH) code += (int) (Math.random() * 10);
            String text = "您的验证码为：" + code + "，2分钟内有效。如有疑问请咨询热线" + organizationInfo.getTelephone() + "。温馨提示:如非本人操作,请忽略此短信!";
            String returnText = SendShortMessageUtil.sendShortMessage(phoneNumber, text, organizationInfo.getOrganizationName());
            log.info("[product]：发送验证码成功：" + returnText);
            // 把验证码和手机号，生成时间绑定
            // 将认证码存入SESSION
            long createTime = new Date().getTime();
            session.setAttribute(appId + "@verifyCode", phoneNumber + "|" + code + "|" + createTime);
        } catch (Exception e) {
            session.removeAttribute("@verifyCode");
            log.error("[product]：发送验证码失败：" + e.getMessage());
        }
    }


    /**
     * 获取微信昵称
     */
    @RequestMapping(value = "/getNickName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getNickName(HttpServletRequest request) {
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, (String) request.getSession().getAttribute("appId"));
        JSONObject userInfo = wechatUtil.getWechatUserInfo(this.wechatUtil.getAccessToken(organizationInfo.getAppId(), organizationInfo.getSecret()), (String) request.getSession().getAttribute("openId"));
        Map<String, String> result = new HashMap<>();
        result.put("nickName", userInfo.getString("nickname"));
        result.put("headImgUrl", userInfo.getString("headimgurl"));
        return result;
    }


    /**
     * 保存手机绑定信息
     */
    @RequestMapping(value = "/saveApplyInfo", method = RequestMethod.POST)
    @ResponseBody
    public BindResultModel saveApplyInfo(@RequestParam("model") String model, HttpServletRequest request) {
        // 从session获取验证信息
        BindResultModel bindResultModel = new BindResultModel();
        try {
            String appId = (String) request.getSession().getAttribute("appId");
            JSONObject json = JSONObject.fromObject(model);
            //校验验证码是否正确
            HttpSession session = request.getSession();
            String codeAndTime = (String) session.getAttribute(appId + "@verifyCode");
            long checkTime = new Date().getTime();
            // 如果超时验证不通过
            if (StringUtils.isEmptyOrNull(codeAndTime)) {
                bindResultModel.setMessage("验证码输入错误，请重新输入！");
                bindResultModel.setSuccess(false);
                return bindResultModel;
            }
            String sendTime = codeAndTime.split("\\|")[2];
            float btTime = (checkTime - Long.parseLong(sendTime)) / (1000);
            // 检测是否频繁发送
            if (LIMIT_TIME < btTime) {
                bindResultModel.setMessage("验证码过期，请重新获取！");
                bindResultModel.setSuccess(false);
                return bindResultModel;
            } else {
                String sessionMobile = codeAndTime.split("\\|")[0];
                String vcode = codeAndTime.split("\\|")[1];
                // 判断输入手机号码和获取验证码手机号码是否一致
                if (!sessionMobile.equals(json.getString("phone"))) {
                    bindResultModel.setMessage("手机号码修改后，请重新获取短信验证码！");
                    bindResultModel.setSuccess(false);
                    return bindResultModel;
                } else if (!vcode.equals(json.getString("verificationCode").trim())) {
                    bindResultModel.setMessage("验证码输入错误，请重新输入！");
                    bindResultModel.setSuccess(false);
                    return bindResultModel;
                } else {
                    WeChatUserBindParam param = new WeChatUserBindParam();
                    String openId = (String) request.getSession().getAttribute("openId");
                    //封装请求参数
                    OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
                    param.setAppId(organizationInfo.getAppId());
                    param.setAppName(organizationInfo.getAppName());
                    param.setCompanyName(json.getString("companyName"));
                    param.setJob(json.getString("job"));
                    param.setName(json.getString("name"));
                    param.setNickName(json.getString("nickName"));
                    param.setOpenId(openId);
                    param.setPhone(json.getString("phone"));
                    param.setOrganizationName(organizationInfo.getOrganizationName());
                    param.setOrganizationId(organizationInfo.getOrganizationId());
                    commonSerice.bind(param);
                    bindResultModel.setMessage("绑定成功");
                    bindResultModel.setSuccess(true);
                    return bindResultModel;
                }
            }
        } catch (Exception e) {
            bindResultModel.setSuccess(false);
            bindResultModel.setMessage(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
            log.error(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
            return bindResultModel;
        }
    }
}
