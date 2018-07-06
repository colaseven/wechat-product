package com.caad.wechat.controller;


import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.PaymentResult;
import com.abc.pay.client.ebus.UnifiedPaymentRequest;
import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.dao.ReportPaymentInfoDao;
import com.caad.wechat.entity.ReportPaymentInfo;
import com.caad.wechat.model.viss.aritificial.NodeModel;
import com.caad.wechat.model.viss.assessAgent.ReportListDto;
import com.caad.wechat.model.viss.assessAgent.WeChatEntrustPaymentDto;
import com.caad.wechat.model.viss.assessAgent.WeChatEntrustSearch;
import com.caad.wechat.model.viss.assessAgent.WeChatReportDetail;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.PagedList;
import com.caad.wechat.service.wechat.IAritificialService;
import com.caad.wechat.service.wechat.IAssessAgentService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.DateUtil;
import com.caad.wechat.utils.viss.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 快捷询价
 */
@RequestMapping("/entrust")
@Controller
public class EntrustController {

    private static Log log = LogFactory.getLog(EntrustController.class);

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
    @Resource(name = "aritificialService")
    private IAritificialService aritificialService;

    /**
     * 接口注入
     */
    @Autowired
    @Resource(name = "assessAgentService")
    private IAssessAgentService assessAgentService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private WechatPayUtil wechatPayUtil;

    @Autowired
    private ReportPaymentInfoDao reportPaymentInfoDao;


    /**
     * 估价目的select
     */
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    @ResponseBody
    public List<NodeModel> getDict(@RequestParam(value = "dictType") String dictType) {

        List<NodeModel> dict = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(this.aritificialService.getDict(dictType));
        for (Object object : jsonArray) {
            dict.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), NodeModel.class));
        }
        log.debug("[entrust]：获取估价目的返回结果" + JSONArray.fromObject(dict));
        return dict;
    }


    /**
     * 获取省市区
     */
    @RequestMapping(value = "/allAreas", method = RequestMethod.GET)
    @ResponseBody
    public List<NodeModel> getAreas(@RequestParam(value = "id", defaultValue = "0") String id) {
        List<NodeModel> areas = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(aritificialService.getAllAreas(Long.parseLong(id)));
        for (Object object : jsonArray) {
            areas.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), NodeModel.class));
        }
        log.debug("[entrust]：获取省市区返回结果" + JSONArray.fromObject(areas));
        return areas;
    }

    /**
     * 获取委托报告历史记录
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public PagedList<ReportListDto> getHistoryList(HttpServletRequest request,
                                                   @RequestParam(value = "pageIndex") int pageIndex,
                                                   @RequestParam(value = "pageSize") int pageSize,
                                                   @RequestParam(value = "search", defaultValue = "") String search,
                                                   @RequestParam(value = "templateTypeId", defaultValue = "") String templateTypeId) {
        WeChatEntrustSearch params = new WeChatEntrustSearch();
        params.setEntrustType(templateTypeId);
        params.setUserOpenId((String) request.getSession().getAttribute("openId"));
        params.setWeChatComCondition(search);
        params.setPageIndex(pageIndex);
        params.setPageSize(pageSize);
        PagedList<ReportListDto> historyList = assessAgentService.getHistoryList(params);
        List<ReportListDto> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(historyList.getItems());
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), ReportListDto.class));
        }
        historyList.setTemplateTypes(historyList.getTemplateTypes());
        historyList.setItems(list);
        log.debug("[entrust]：获取委托报告历史记录结果：" + JSONArray.fromObject(historyList));
        return historyList;
    }


    /**
     * 委托报告详情
     */
    @RequestMapping(value = "/particulars", method = RequestMethod.GET)
    @ResponseBody
    public WeChatReportDetail getParticulars(HttpServletRequest request,
                                             @RequestParam(value = "entrustId") long entrustId,
                                             @RequestParam(value = "appId", defaultValue = "") String appId,
                                             @RequestParam(value = "openId", defaultValue = "") String openId) {
        String telephone = "";
        if (StringUtils.isNotEmptyOrNull(appId)) {
            HttpSession session = request.getSession();
            session.invalidate();
            OrganizationWeChatSettingResult organizationInfo = commonUtil.getOrganizationBySession(request, appId);
            telephone = organizationInfo.getTelephone();
            request.getSession().setAttribute("appId", appId);
            request.getSession().setAttribute("openId", openId);
        }
        WeChatReportDetail reportDetail = assessAgentService.getReportDetail(entrustId);
        if (StringUtils.isNotEmptyOrNull(telephone)) {
            reportDetail.setTelephone(telephone);
        }
        log.debug("[entrust]：获取委托报告单据详情结果：" + JSONObject.fromObject(reportDetail));
        return reportDetail;
    }


    /**
     * 处理支付请求参数封装、校验并调用微信SDK付款
     */
    @RequestMapping(value = "/payment", method = {RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings("unchecked")
    public JSONObject payment(HttpServletRequest request,
                              @RequestParam(value = "price") String price,//价格
                              @RequestParam(value = "idList") String idList,
                              @RequestParam(value = "entrustId") String entrustId) {
        String orderNo = System.currentTimeMillis() + WechatPayUtil.genRandomStr(10);//当前订单号
        //从session中获取 商户Id 商户秘钥等
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
        String mchKey = organizationInfo.getPayPassWord();//商户秘钥
        String mchId = organizationInfo.getBusinessId();//商户Id
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
                orderDetail.put("ProductName", "委托报告");
                param.orderitems.put("orderDetail", orderDetail);
                //设定支付请求对象的属性
                param.dicRequest.put("PaymentType", "8");
                param.dicRequest.put("PaymentLinkType", "2");
                param.dicRequest.put("NotifyType", "1");
                param.dicRequest.put("ResultNotifyURL", VISS_BASEURL + "entrust/abcNotify.do");
                param.dicRequest.put("IsBreakAccount", "0");
                abcResult = param.postRequest();
            } else {
                log.info("[entrust]：获取支付商户Id：" + mchId);
                log.info("[entrust]：获取支付商户秘钥：" + mchKey);
                Map<String, String> params = new HashMap<>();
                params.put("appid", appId);
                params.put("body", "委托报告");//商品描述
                params.put("device_info", "WEB");//设备号
                params.put("mch_id", mchId);// 商户号
                params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
                params.put("notify_url", VISS_BASEURL + "entrust/notify.do");//服务器异步通知页面路径
                params.put("openid", openId);
                params.put("out_trade_no", orderNo);//商户订单号
                params.put("total_fee", String.valueOf(Math.round((Double.parseDouble(price) * 100))));//总金额 ，单位为分
                params.put("trade_type", "JSAPI");//交易类型 SAPI--公众号支付
                params.put("spbill_create_ip", wechatPayUtil.getHostIp());
                params.put("sign", wechatPayUtil.getSign(params, mchKey));//支付秘钥
                log.debug("[entrust]：请求支付开始准备参数：" + JSONObject.fromObject(params));
                String httpResult = wechatPayUtil.postHttplient(WechatConstant.UNIFIED_ORDER_URL, wechatPayUtil.mapToXml(params));
                paymentResultInfo = wechatPayUtil.xmlToMap(httpResult);
                log.info("[entrust]：调用支付接口获取到的数据：" + JSONObject.fromObject(paymentResultInfo));
                log.info("[entrust]：获取订单号：" + paymentResultInfo.get("prepay_id"));
            }
            //保存数据库开始
            ReportPaymentInfo reportPaymentInfo = new ReportPaymentInfo();
            reportPaymentInfo.setAppid(appId);
            if (appId.equals("wx4274ce8a60e68b5d")) {
                reportPaymentInfo.setBody("委托报告-农行支付");
            } else {
                reportPaymentInfo.setBody("委托报告-微信支付");
            }
            reportPaymentInfo.setOpenid(openId);
            reportPaymentInfo.setBoughtNumber(orderNo);
            reportPaymentInfo.setProductName("委托报告");
            reportPaymentInfo.setTotal_fee(price);
            reportPaymentInfo.setToBePaidIdList(idList);
            reportPaymentInfo.setEntrustId(entrustId);
            reportPaymentInfo.setTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
            reportPaymentInfo.setIsNotifySuccess("start");
            reportPaymentInfoDao.save(reportPaymentInfo);
            //保存数据库结束
            Map<String, String> result = new HashMap<>();
            if (appId.equals("wx4274ce8a60e68b5d")) {
                result.put("appId", abcResult.GetKeyValue("sub_appId"));
                result.put("timeStamp", abcResult.GetKeyValue("timeStamp"));
                result.put("nonceStr", abcResult.GetKeyValue("nonceStr"));
                result.put("package", abcResult.GetKeyValue("package"));
                result.put("signType", abcResult.GetKeyValue("signType"));
                result.put("paySign", abcResult.GetKeyValue("paySign"));
                log.info("[entrust]：农行支付回调返回成功页面数据=" + JSONObject.fromObject(result));
                return JSONObject.fromObject(result);
            } else {
                result.put("appId", appId);
                result.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
                result.put("nonceStr", paymentResultInfo.get("nonce_str"));
                result.put("package", "prepay_id=" + paymentResultInfo.get("prepay_id"));
                result.put("signType", "MD5");
                result.put("paySign", wechatPayUtil.getSign(result, mchKey));
                log.debug("[entrust]：微信支付回调返回成功页面数据=" + JSONObject.fromObject(result));
                return JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 农行支付回调
     */
    @RequestMapping(value = "abcNotify")
    @ResponseBody
    public void abcNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = request.getParameter("MSG");
        PaymentResult tResult = new PaymentResult(msg);
        Map<String, String> map = wechatPayUtil.xmlToMap(tResult.getResponseMessage());
        log.info("[entrust]：农行支付获取回调数据:" + JSONObject.fromObject(map).toString());
        ReportPaymentInfo reportPaymentInfo = reportPaymentInfoDao.findByBoughtNumber(map.get("OrderNo"));
        String return_code;
        if (tResult.isSuccess()) {
            boolean notifyBoolean = reportPaymentInfo.getIsNotifySuccess().equals("start") || reportPaymentInfo.getIsNotifySuccess().equals("false");
            String appId = reportPaymentInfo.getAppid();
            request.getSession().setAttribute("appId", appId);
            OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
            if (notifyBoolean) {
                try {
                    //将支付接口信息进行回调
                    log.info("[entrust]：农行支付回调开始：" + JSONObject.fromObject(reportPaymentInfo));
                    WeChatEntrustPaymentDto param = new WeChatEntrustPaymentDto();
                    param.setBoughtNumber(reportPaymentInfo.getBoughtNumber());//订单号
                    param.setCompanyId(reportPaymentInfo.getAppid());//appId
                    param.setCompanyName(organizationInfo.getAppName());//微信公众号名称
                    param.setEntrustPaymentIds(reportPaymentInfo.getToBePaidIdList());
                    param.setOrganizationId(organizationInfo.getOrganizationId());
                    param.setOrganizationName(organizationInfo.getOrganizationName());
                    param.setPrice(reportPaymentInfo.getTotal_fee());
                    param.setTime(reportPaymentInfo.getTime());
                    param.setEntrustId(reportPaymentInfo.getEntrustId());
                    JSONObject userInfo = wechatUtil.getWechatUserInfo(this.wechatUtil.getAccessToken(organizationInfo.getAppId(), organizationInfo.getSecret()), reportPaymentInfo.getOpenid());
                    param.setUserName(userInfo.getString("nickname"));
                    param.setUserOpenId(reportPaymentInfo.getOpenid());
                    param.setWeChatPayInfo(JSONObject.fromObject(map));
                    notifyBoolean = this.assessAgentService.weChatConfirmPayment(param);
                    log.info("[entrust]：农行支付回调成功数据：" + JSONObject.fromObject(param));
                    return_code = "SUCCESS";
                } catch (Exception e) {
                    log.error("[entrust]：农行支付回调失败：" + e.getMessage());
                    notifyBoolean = false;
                    return_code = "FAIL";
                }
                reportPaymentInfo.setIsNotifySuccess(String.valueOf(notifyBoolean));
            } else {
                reportPaymentInfo.setIsNotifySuccess("false");
                return_code = "FAIL";
            }
        } else {
            reportPaymentInfo.setIsNotifySuccess("false");
            return_code = "FAIL";
        }
        reportPaymentInfoDao.save(reportPaymentInfo);
        response.getWriter().write(return_code);
    }

    /**
     * 微信支付回调
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
        log.info("开始支付回调中。。。。" + xmlString);
        Map<String, String> map = wechatPayUtil.xmlToMap(xmlString);
        log.info("[entrust]：支付回调获取到支付信息：" + JSONObject.fromObject(map));
        request.getSession().setAttribute("appId", map.get("appid"));
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, map.get("appid"));
        ReportPaymentInfo reportPaymentInfo = reportPaymentInfoDao.findByBoughtNumber(map.get("out_trade_no"));
        boolean notifyBoolean = reportPaymentInfo.getIsNotifySuccess().equals("start") || reportPaymentInfo.getIsNotifySuccess().equals("false");
        log.info("[entrust]：支付回调判断：" + notifyBoolean);
        String return_code = "";
        if (map.get("return_code").equals("SUCCESS") && notifyBoolean) {
            try {
                //将支付接口信息进行回调
                log.info("[entrust]：支付回调开始：" + JSONObject.fromObject(reportPaymentInfo));
                WeChatEntrustPaymentDto param = new WeChatEntrustPaymentDto();
                param.setBoughtNumber(reportPaymentInfo.getBoughtNumber());//订单号
                param.setCompanyId(reportPaymentInfo.getAppid());//appId
                param.setCompanyName(organizationInfo.getAppName());//微信公众号名称
                param.setEntrustPaymentIds(reportPaymentInfo.getToBePaidIdList());
                param.setOrganizationId(organizationInfo.getOrganizationId());
                param.setOrganizationName(organizationInfo.getOrganizationName());
                param.setPrice(reportPaymentInfo.getTotal_fee());
                param.setTime(reportPaymentInfo.getTime());
                param.setEntrustId(reportPaymentInfo.getEntrustId());
                JSONObject userInfo = wechatUtil.getWechatUserInfo(this.wechatUtil.getAccessToken(organizationInfo.getAppId(), organizationInfo.getSecret()), reportPaymentInfo.getOpenid());
                param.setUserName(userInfo.getString("nickname"));
                param.setUserOpenId(reportPaymentInfo.getOpenid());
                param.setWeChatPayInfo(JSONObject.fromObject(map));
                notifyBoolean = this.assessAgentService.weChatConfirmPayment(param);
                log.info("[entrust]：支付回调成功数据：" + JSONObject.fromObject(param));
                return_code = "SUCCESS";
            } catch (Exception e) {
                log.error("[entrust]：支付回调失败：" + e.getMessage());
                notifyBoolean = false;
                return_code = "FAIL";
            }
            reportPaymentInfo.setIsNotifySuccess(String.valueOf(notifyBoolean));
        } else if (map.get("return_code").equals("FAIL")) {
            reportPaymentInfo.setIsNotifySuccess("false");
            return_code = "FAIL";
        }
        reportPaymentInfoDao.save(reportPaymentInfo);
        response.getWriter().write("<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
    }


    /**
     * 用于测试农行支付接口调用
     */
    @RequestMapping(value = "/abcPayTest", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String abcPayTest(@RequestParam("model") String model) {
        JSONObject json = JSONObject.fromObject(model);
        UnifiedPaymentRequest param = new UnifiedPaymentRequest();
        log.info("[entrust]:开始调用农行支付接口中。。。");
        //设置订单的属性
        param.dicOrder.put("PayTypeID", json.getString("PayTypeID"));
        param.dicOrder.put("OrderDate", json.getString("OrderDate"));
        param.dicOrder.put("OrderTime", json.getString("OrderTime"));
        param.dicOrder.put("CurrencyCode", json.getString("CurrencyCode"));//交易币种 156人民币
        param.dicOrder.put("OrderNo", json.getString("OrderNo"));
        param.dicOrder.put("OrderAmount", json.getString("OrderAmount"));
        param.dicOrder.put("AccountNo", json.getString("AccountNo"));//
        param.dicOrder.put("OpenID", json.getString("OpenID"));
        param.dicOrder.put("InstallmentMark", json.getString("InstallmentMark"));
        param.dicOrder.put("CommodityType", json.getString("CommodityType"));
        param.dicOrder.put("OrderDesc", json.getString("OrderDesc"));
        //将订单明细加入订单中
        LinkedHashMap orderDetail = new LinkedHashMap();
        orderDetail.put("ProductName", json.getString("ProductName"));
        param.orderitems.put("orderDetail", orderDetail);
        //设定支付请求对象的属性
        param.dicRequest.put("PaymentType", json.getString("PaymentType"));
        param.dicRequest.put("PaymentLinkType", json.getString("PaymentLinkType"));
        param.dicRequest.put("NotifyType", json.getString("NotifyType"));
        param.dicRequest.put("ResultNotifyURL", json.getString("ResultNotifyURL"));
        param.dicRequest.put("IsBreakAccount", json.getString("IsBreakAccount"));
        log.info("[entrust]:调用农行接口支付请求请求参数：" + JSONObject.fromObject(param).toString());
        JSON result = param.postRequest();
        String ABCreturnCode = result.GetKeyValue("ReturnCode");//辨别是否请求成功
        String errorMessage = result.GetKeyValue("ErrorMessage");
        String paymentURL = result.GetKeyValue("PaymentURL");
        log.info("[entrust]:请求厦门农行支付接口返回支付页面网址：" + paymentURL);
        log.info("[entrust]:请求厦门农行支付接口返回MerchantID：" + result.GetKeyValue("MerchantID"));
        log.info("[entrust]:请求厦门农行支付接口返回TrxType：" + result.GetKeyValue("TrxType"));
        log.info("[entrust]:请求厦门农行支付接口返回OrderNo：" + result.GetKeyValue("OrderNo"));
        log.info("[entrust]:请求厦门农行支付接口返回OrderAmount：" + result.GetKeyValue("OrderAmount"));
        log.info("[entrust]:请求厦门农行支付接口返回HostDate：" + result.GetKeyValue("HostDate"));
        log.info("[entrust]:请求厦门农行支付接口返回HostTime：" + result.GetKeyValue("HostTime"));
        log.info("[entrust]:请求厦门农行支付接口返回PrePayID：" + result.GetKeyValue("PrePayID"));
        log.info("[entrust]:请求厦门农行支付接口返回结果：" + ABCreturnCode);
        log.info("[entrust]:请求厦门农行支付接口错误信息：" + result.GetKeyValue("ErrorMessage"));
        return ABCreturnCode + "-----" + errorMessage;
    }
}
