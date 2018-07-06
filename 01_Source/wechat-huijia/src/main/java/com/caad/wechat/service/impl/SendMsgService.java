package com.caad.wechat.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.caad.wechat.dao.HuijiaReqDao;
import com.caad.wechat.dao.UserBindOpDao;
import com.caad.wechat.entity.HuijiaReq;
import com.caad.wechat.entity.UserBindOp;
import com.caad.wechat.enums.HuijiaReqStatus;
import com.caad.wechat.enums.UserBindOpStatus;
import com.caad.wechat.model.caad.TextResponseMessage;
import com.caad.wechat.model.WxTemplate;
import com.caad.wechat.service.MsgProcessor;
import com.caad.wechat.service.MsgProcessorByPatterns;
import com.caad.wechat.util.GenNewId;
import com.caad.wechat.util.MessageUtil;
import com.caad.wechat.util.SpringDataJpaBase;
import com.caad.wechat.util.UserUtil;
import com.caad.wechat.util.WeixinUtil;
import com.caadt.cln.common.util.DateUtil;
import com.caadt.cln.common.util.HttpUtil;
import com.caadt.cln.common.util.QueryListUtil;
import com.caadt.cln.common.util.StringUtils;

import net.sf.json.JSONObject;

@Service("sendMsg")
public class SendMsgService {

    private static final String OPEN_ID_STR = "openId";

    private static final String MESSAGE_STR = "message";

    private static Log log = LogFactory.getLog(SendMsgService.class);

    @Autowired
    private GenNewId genNewId;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private WeixinUtil weixinUtil;
    @Autowired
    private HuijiaReqDao huijiaReqDao;
    @Autowired
    private SpringDataJpaBase springDataJpaBase;
    @Autowired
    private UserBindOpDao userBindOpDao;
    @Autowired
    private HttpUtil httpClientTemplateToDataConfirm;// 请求回应总价接口

    @Value("#{settings['wechat.appid']}") // AppID(应用ID)
    private String APPID;

    @Value("#{settings['wechat.appsecret']}") // AppSecret(应用密钥)
    private String APPSECRET;

    @Value("#{settings['wechat.back.price.template']}") // 待回价业务通知模板
    private String BACKPRICETEMPLATE;

    @Value("#{settings['wechat.sendMesgTemplate']}") // 发送模板消息
    private String SENDMESGTEMPLATE;

    @Value("#{settings['wechat.success.template']}") // 操作成功通知模板
    private String SUCCESSTEMPLATE;

    @Value("#{settings['wechat.myAccount.template']}") // 我的账户模板
    private String MYACCOUNTTEMPLATE;

    @Value("#{settings['menu.dispatcher.url']}") // 菜单跳转连接地址
    private String DISPATCHERURL;

    @Value("#{settings['wechat.pending.message']}") // 菜单跳转连接地址
    private String PENDINGMSG;

    /**
     * 待回价模板通知 发送模板消息 appId 公众账号的唯一标识 appSecret 公众账号的密钥 openId 用户标识
     */

    public void sendTemplateMessage(JSONObject json, String loginname) {

        log.info("[x11,准备]模板消息1发送。");

        if (StringUtils.isEmptyOrNull(loginname)) {
            log.info("[x12,失败]模板消息1发送，loginname为空。");
            return;
        }

        String openId = this.userUtil.getOpenId(loginname);
        if (StringUtils.isEmptyOrNull(openId)) {
            log.info("[x13,失败]模板消息1发送，openId为空。");
            return;
        }

        HuijiaReq huijiaReq = new HuijiaReq();// 写入数据库
        huijiaReq.setOpenid(openId);
        huijiaReq.setLoginname(loginname);
        huijiaReq.setVissid(json.getString("Id"));
        huijiaReq.setMiniid(String.valueOf(this.genNewId.genNewId()));
        huijiaReq.setCreatetime(new Date());
        huijiaReq.setJson((json).toString());
        huijiaReq.setStatus(HuijiaReqStatus.Create.flag);
        huijiaReq.setDeadlinetime(StringUtils.isNotEmptyOrNull(json.getString("DeadLineTime")) ? json.getString("DeadLineTime") : "");
        this.huijiaReqDao.save(huijiaReq);

        WxTemplate wxtemplate = new WxTemplate();
        wxtemplate.setUrl(json.getString("Url"));
        wxtemplate.setTouser(openId);
        wxtemplate.setTopcolor("#000000");
        wxtemplate.setTemplate_id(this.BACKPRICETEMPLATE);
        wxtemplate.addDataItem("first", "#0000FF", "您有一条新业务需要回价");
        wxtemplate.addDataItem("keyword1", "#0000FF", json.getString("CompanyName"));
        wxtemplate.addDataItem("keyword2", "#0000FF", json.getString("DetailAddress"));
        wxtemplate.addDataItem("keyword3", "#0000FF", json.getString("FloorAcreage") + "平方米");
        wxtemplate.addDataItem("keyword4", "#0000FF", huijiaReq.getLoginname());
        boolean bln = !json.containsKey("HistorySubmitTime") || !json.containsKey("HistorySinglePrice");
        String surveyMsg = json.getBoolean("IsOrientation") ? "\n提示：此业务需要查勘，请在平台上完成查勘业务后再进行价格评估！" : "";
        if (StringUtils.isNotEmptyOrNull(huijiaReq.getDeadlinetime())) {
            wxtemplate.addDataItem("remark", "#000000", String.format("请在%s前回复， “%s-单价（元/㎡）”【例：%s-8953】或直接点此回价。"
                    + surveyMsg
                    + (bln ? "" : "（注：该物业于%s已回价【%s元/㎡】）"), huijiaReq.getDeadlinetime(), huijiaReq.getMiniid(), huijiaReq.getMiniid(), bln ? "" :
                    json.getString("HistorySubmitTime"), bln ? "" : json.getString("HistorySinglePrice")));
        } else {
            wxtemplate.addDataItem("remark", "#000000", String.format("请回复， “%s-单价（元/㎡）”【例：%s-8953】或直接点此回价。"
                    + surveyMsg
                    + (bln ? "" : "（注：该物业于%s已回价【%s元/㎡】）"), huijiaReq.getMiniid(), huijiaReq.getMiniid(), bln ? "" : json.getString("HistorySubmitTime"), bln ? "" : json.getString("HistorySinglePrice")));
        }
        String jsonString = JSONObject.fromObject(wxtemplate).toString();

        String access_token = this.weixinUtil.getAccessToken(this.APPID, this.APPSECRET);
        String url = this.SENDMESGTEMPLATE + access_token;
        log.info("[x15,发送]回价模板消息发送中" + url);
        JSONObject jsonObject = this.weixinUtil.httpRequest(url, "POST", jsonString);
        int result;
        if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            log.info(String.format("[x18,错误]回价模板消息发送异常:errcode:{%s} errmsg:{%s}", result, jsonObject.getString("errmsg")));
        } else {
            log.info("[x19,成功]回价模板消息发送完成：" + jsonObject);
        }
    }

    /**
     * 对接收的消息进行处理响应
     */
    public String onReceiveTextMsg(String text, String openId) {
        log.info(String.format("消息的接收、处理、响应,%s,%s.", openId, text));
        if (text == null) {
            return null;
        }
        text = text.trim();

        Map<String, String> params = new HashMap<>();
        params.put(MESSAGE_STR, text);
        params.put(OPEN_ID_STR, openId);
        for (MsgProcessorByPatterns msgProcessorByPatterns : this.arympbp) {
            if (msgProcessorByPatterns.test(text)) {
                return msgProcessorByPatterns.process(params);
            }
        }
        return this.msgProcessorDefault.process(params);
    }

    /**
     * 预定义的默认处理器
     */
    private MsgProcessor msgProcessorDefault = param -> {

        StringBuffer sb = new StringBuffer();
        sb.append("输入的格式或内容不正确，请重新输入。\n");
        sb.append("输入格式：\n");
        sb.append("☞绑定：账户 账户名 密码\n");
        sb.append("（例：账户 lisi 123）\n");
        sb.append("☞解绑：账户- 账户名 密码\n");
        sb.append("（例：账户- lisi 123）\n");
        sb.append("☞回价：编号-单价\n");
        sb.append("（例：204-9000）\n");
        sb.append("☞回价确认：“是”或“Y”");
        return sb.toString();
    };

    /**
     * 预定义的各类处理器
     */
    private MsgProcessorByPatterns[] arympbp = new MsgProcessorByPatterns[]{

            new MsgProcessorByPatterns(new String[]{"^1$"}, new MsgProcessor() {// 用户绑定确认回复1。

                @Override
                public String process(Map<String, String> param) {
                    return bindingUserInfo(param.get(MESSAGE_STR), param.get(OPEN_ID_STR));
                }

                /**
                 * 用户绑定确认回复1
                 */
                private String bindingUserInfo(String text, String openid) {
                    log.info("获取用户发送消息" + text);
                    UserBindOp userBindOp = SendMsgService.this.springDataJpaBase.findOne(UserBindOp.class, openid);

                    if (userBindOp == null || System.currentTimeMillis() - userBindOp.getCreatetime().getTime() >= 2 * 60 * 1000) {
                        return "输入有误或输入超时，请核对后再做输入！";
                    }

                    if (!userBindOp.getStatus().equals(UserBindOpStatus.CreateWithDuplicatedOpenid.flag) && !userBindOp.getStatus().equals(UserBindOpStatus.CreateWithDuplicatedLoginname.flag)) {
                        return "输入有误或输入超时，请核对后再做输入！";
                    }
                    String result1 = SendMsgService.this.userUtil.deleteOpenId(openid);//解绑
                    String result2 = SendMsgService.this.userUtil.deleteUser(userBindOp.getLoginname());//解绑
                    userBindOp.setRemoveddate(result1 + "\t" + result2);
                    SendMsgService.this.userUtil.saveUser(openid, userBindOp.getLoginname());//绑定新的用户信息
                    userBindOp.setStatus(UserBindOpStatus.DoneWithDuplicatedOpenid.flag);
                    SendMsgService.this.userBindOpDao.save(userBindOp);
                    return "绑定新账户成功，回价信息会自动发送到本微信账号";
                }
            }),

//			new MsgProcessorByPatterns(new String[] { "^MYACCOUNT$" }, new MsgProcessor() {// 点击菜单“MYACCOUNT”。
//
//				@SuppressWarnings("deprecation")
//				@Override
//				public String process(Map<String, String> param) {
//					try {
//						Map<String, String> map = new HashMap<>();
//						map.put("username", SendMsgService.this.userUtil.getLoginName(param.get(OPEN_ID_STR)));
//						String content = EncryptUtil.encrypt(JSONObject.fromObject(map));
//						String vissUrl = SendMsgService.this.DISPATCHERURL + URLEncoder.encode(content);
//						log.info("加密后菜单跳转url：" + vissUrl);
//						WxTemplate wxtemplate = new WxTemplate();
//						wxtemplate.setTemplate_id(SendMsgService.this.MYACCOUNTTEMPLATE);
//						wxtemplate.setTouser(param.get(OPEN_ID_STR));
//						wxtemplate.setUrl(vissUrl);
//						wxtemplate.addDataItem("keyword1", "#0000FF", "我的账户");
//						wxtemplate.addDataItem("keyword2", "#0000FF", SendMsgService.this.userUtil.getLoginName(param.get(OPEN_ID_STR)));
//						wxtemplate.addDataItem("keyword3", "#0000FF", DateUtil.getDate("yyyy-MM-dd HH:mm:ss", new Date()));
//						String jsonString = JSONObject.fromObject(wxtemplate).toString();
//						String access_token = SendMsgService.this.weixinUtil.getAccessToken(SendMsgService.this.APPID, SendMsgService.this.APPSECRET);
//						String url = SendMsgService.this.SENDMESGTEMPLATE + access_token;
//						JSONObject jsonObject = SendMsgService.this.weixinUtil.httpRequest(url, "POST", jsonString);
//						int result = 0;
//						if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
//							result = jsonObject.getInt("errcode");
//						}
//						log.info("发送我的账户模板消息成功：" + result);
//					} catch (Exception e) {
//						log.debug("url携带参数加密发生异常：" + e.getMessage());
//					}
//					return null;
//				}
//
//			}),

            new MsgProcessorByPatterns(new String[]{"^是$|^Y$"}, new MsgProcessor() {// 是、Y，确认回价提交。

                @Override
                public String process(Map<String, String> param) {
                    return onReceiveMsgToYes(param.get(OPEN_ID_STR));
                }

                /**
                 * 回价确认
                 * @param openId 微信用户标识
                 * @return 文本
                 */
                private String onReceiveMsgToYes(String openId) {
                    log.info("[x30,收到]是-----" + openId);
                    HuijiaReq huijiaReq = queryOne(openId, "2", null, null);
                    if (huijiaReq == null || StringUtils.isEmptyOrNull(huijiaReq.getAssessTotalPrice()) || StringUtils.isEmptyOrNull(huijiaReq.getAssessSinglePrice())) {
                        log.info("[x31,错误]未输入业务单号及单价" + huijiaReq);
                        return "请先输入\"业务单号-单价\"对业务单据进行回价";
                    }
                    log.info("[x32,收到]回价文本消息");

                    String deadlinetime = huijiaReq.getDeadlinetime();
                    if (StringUtils.isNotEmptyOrNull(deadlinetime)) {
                        Date dateLine = DateUtil.parseToDate(deadlinetime);
                        if (isExpired(dateLine)) {
                            log.info("[33,错误]单据已过期" + dateLine);
                            return "回价失败，此单据已过期，请重新核实后再作输入。";
                        }
                    }
                    Map<String, String> params = new HashMap<>();
                    params.put("id", huijiaReq.getVissid());
                    params.put("text", huijiaReq.getMiniid() + "-" + huijiaReq.getAssessSinglePrice());
                    log.info(String.format("[x35,发送]回价确认中：%s,%s", huijiaReq.getAssessTotalPrice(), huijiaReq.getAssessSinglePrice()));
                    huijiaReq.setSubmittime(new Date());
                    //
                    String result = SendMsgService.this.httpClientTemplateToDataConfirm.invoke(params);
                    log.info("[x36,成功]回价确认完成" + result);
                    JSONObject resultJson = JSONObject.fromObject(result);
                    if (!resultJson.getBoolean("Data")) {
                        huijiaReq.setStatus(HuijiaReqStatus.Create.flag);// 从数据库取出数据在进行新增状态
                        SendMsgService.this.huijiaReqDao.save(huijiaReq);
                        log.info("[x37,错误]回价失败" + String.format("回价失败,%s", resultJson.getString("Message")));
                        return String.format("回价失败,%s", resultJson.getString("Message"));
                    }
                    huijiaReq.setStatus(HuijiaReqStatus.Submit.flag);// 从数据库取出数据在进行新增状态
                    SendMsgService.this.huijiaReqDao.save(huijiaReq);
                    log.info("[x38,成功]回价确认完成" + result);
                    return null;
                }
            }),

            new MsgProcessorByPatterns(new String[]{"^账户 .+ .+"}, new MsgProcessor() {// 账户/帐户 用户名、密码，账户关联。

                @Override
                public String process(Map<String, String> param) {
                    return onReceiveMsgToAddAccount(param.get(MESSAGE_STR), param.get(OPEN_ID_STR));
                }

                /**
                 * 处理“账户 szyladmin szyladmin123”。
                 * @param text 获取的文本
                 * @param openId 微信用户标识
                 * @return 文本
                 */
                private String onReceiveMsgToAddAccount(String text, String openId) {
                    UserBindOp userBindOp = new UserBindOp();
                    userBindOp.setStatus(UserBindOpStatus.CreateAndDone.flag);
                    text = text.substring(3).trim();
                    int p = text.indexOf(" ");
                    String loginname = SendMsgService.this.userUtil.formatLoginName(text.substring(0, p).trim());
                    String password = text.substring(p + 1).trim();
                    boolean bln = SendMsgService.this.userUtil.checkLoginNamePswdViaSSO(loginname, password);
                    if (!bln) {
                        return "账户关联失败，账号密码验证失败。";
                    } else if (openId.equals(StringUtils.dealEmpty(SendMsgService.this.userUtil.getOpenId(loginname)))) {// 重复绑定
                        return "该账号已与本微信号绑定，无需重复操作。";

                    } else if (StringUtils.isNotEmpty(SendMsgService.this.userUtil.getLoginName(openId))) {
                        userBindOp.setOpenid(openId);
                        userBindOp.setLoginname(loginname);
                        userBindOp.setCreatetime(new Date());
                        userBindOp.setStatus(UserBindOpStatus.CreateWithDuplicatedLoginname.flag);
                        SendMsgService.this.userBindOpDao.save(userBindOp);
                        return "您的微信已与VISS账户“" + SendMsgService.this.userUtil.getLoginName(openId) + "”绑定，绑定新账号“" + loginname + "”将自动解绑老账户。是否确认继续？回复“1”确认绑定新账户。";
                    } else if (StringUtils.isNotEmpty(SendMsgService.this.userUtil.getOpenId(loginname))) {
                        userBindOp.setOpenid(openId);
                        userBindOp.setLoginname(loginname);
                        userBindOp.setCreatetime(new Date());
                        userBindOp.setStatus(UserBindOpStatus.CreateWithDuplicatedOpenid.flag);
                        SendMsgService.this.userBindOpDao.save(userBindOp);
                        return "您的VISS账户“" + loginname + "”已与其他微信号绑定，继续绑定该微信将自动与其他微信解绑。是否确认继续？回复“1”确认绑定新账户。";

                    } else {
                        SendMsgService.this.userUtil.saveUser(openId, loginname);// 绑定成功
                        return "感谢您的关注，账户关联成功，回价信息会自动发送到本微信账号。";
                    }
                }
            }),

            new MsgProcessorByPatterns(new String[]{"^账户\\- .+ .+"}, new MsgProcessor() {// 账户-/帐户-开头，取消用户关联。

                @Override
                public String process(Map<String, String> param) {
                    return onReceiveMsgToDeleteAccount(param.get(MESSAGE_STR), param.get(OPEN_ID_STR));
                }

                /**
                 * 取消关联账户信息
                 * @param text 获取的文本
                 * @param openId 微信用户标识
                 * @return 文本
                 */
                private String onReceiveMsgToDeleteAccount(String text, String openId) {
                    text = text.substring(3).trim();
                    int p = text.indexOf(" ");
                    String loginname = SendMsgService.this.userUtil.formatLoginName(text.substring(0, p).trim());
                    String password = text.substring(p + 1).trim();
                    boolean bln = SendMsgService.this.userUtil.checkLoginNamePswdViaSSO(loginname, password);
                    if (!bln) {
                        return "账户取消关联失败，账号密码验证失败。";
                    }
                    String baseOpenId = SendMsgService.this.userUtil.getOpenId(loginname);
                    if (baseOpenId == null || baseOpenId.equals("")) {
                        return "账号未绑定,无须解绑";
                    } else if (!baseOpenId.equals(openId)) {
                        return "账号未绑定此微信号,无法解绑";
                    }
                    SendMsgService.this.userUtil.deleteUser(loginname);
                    return "解绑成功";
                }

            }),

            new MsgProcessorByPatterns(new String[]{"^\\d+-\\d+$"}, new MsgProcessor() {// 1234-56789，回价处理。

                @Override
                public String process(Map<String, String> param) {
                    return onReceiveMsgToShowPrice(param.get(MESSAGE_STR), param.get(OPEN_ID_STR));
                }

                /**
                 * 回价处理
                 * @param text 获取的文本
                 * @param openId 微信用户标识
                 * @return 文本
                 */
                private String onReceiveMsgToShowPrice(String text, String openId) {

                    String[] param = text.split("-");
                    log.info("[x20,收到]回价”小号-单价”消息" + param[0]);
                    log.info("[x20,收到]回价”openId" + openId);
                    HuijiaReq huijiaReq = queryOne(openId, "1", param[0], null);// 从数据库取出数据在进行修改
                    if (huijiaReq == null) {//TODO
                        log.info("[x21,错误]未找到未回价的指定单据或过期");
                        return "回价失败，未找到此单据或已回价，请重新核实后再作输入。";
                    }
                    JSONObject json = JSONObject.fromObject(huijiaReq.getJson());
                    String deadlinetime = huijiaReq.getDeadlinetime();
                    if (StringUtils.isNotEmptyOrNull(deadlinetime)) {
                        if (isExpired(DateUtil.parseToDate(deadlinetime))) {
                            log.info("[x21,错误]未找到未回价的指定单据或过期" + huijiaReq);
                            return "回价失败，此单据已过期，请重新核实后再作输入。";
                        }
                    }

                    double area = Double.parseDouble(json.getString("FloorAcreage"));
                    double totalPrice = (Double.parseDouble(param[1]) * area) / 10000;
                    BigDecimal b = new BigDecimal(totalPrice);
                    b = b.setScale(1, BigDecimal.ROUND_HALF_UP);
                    log.info("[x25,开始]从数据库读取数据" + huijiaReq);
                    huijiaReq.setAssessSinglePrice(param[1]);
                    huijiaReq.setAssessTotalPrice(String.valueOf(b));
                    huijiaReq.setStatus(HuijiaReqStatus.assess.flag);
                    huijiaReq.setAssesstime(new Date());
                    SendMsgService.this.huijiaReqDao.save(huijiaReq);
                    log.info("[x26,发送]回价”小号-单价”消息" + json);
                    String strformat = "收到您对(%s)号 \"%s\" \r\n\"面积(%s)平方米\"的业务回价为:\r\n单价:%s元/平方米\r\n(即总价:%s万元)\r\n是否确认? 确认回\"是\",修改请重新输入";
                    String string = String.format(strformat, huijiaReq.getMiniid(), json.getString("DetailAddress"), json.getString("FloorAcreage"), huijiaReq.getAssessSinglePrice(), huijiaReq.getAssessTotalPrice());
                    log.info("[x27,成功]回价文本消息成功" + string);
                    return string;
                }
            }),};

    /**
     * 推送文本消息至用户
     *
     * @param text       获取的文本
     * @param openId     微信用户标识
     * @param msgType    发送类型
     * @param wechatName 微信公众号
     * @return 文本
     */
    public String toTextMsgXml(String text, String openId, String msgType, String wechatName) {
        String respContent = "请求处理异常，请稍候尝试！";// 默认返回的文本消息内容

        TextResponseMessage textMessage = new TextResponseMessage();// 回复文本消息
        textMessage.setToUserName(openId);
        textMessage.setFromUserName(wechatName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(msgType);
        textMessage.setFuncFlag(0);
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {// 文本消息
            respContent = text;
        }
        textMessage.setContent(respContent);
        String respMessage = MessageUtil.textMessageToXml(textMessage);
        log.info("推送的文本消息" + respContent);
        return respMessage;
    }

    /**
     * 操作成功通知模板推送
     *
     * @param vissid    vissId
     * @param text      文本
     * @param loginname 绑定账户
     */
    public void sendSuccessMessage(String vissid, String text, String loginname) {
        log.info("[x41,准备]模板消息2发送。");
        if (StringUtils.isEmptyOrNull(loginname)) {
            log.info("[x42,失败]模板消息2发送，loginname为空。");
            return;
        }
        String openId = this.userUtil.getOpenId(loginname);
        if (StringUtils.isEmptyOrNull(openId)) {
            log.info("[x43,失败]模板消息2发送，openId为空。");
            return;
        }
        HuijiaReq huijiaReq = queryOne(openId, null, null, vissid);
        if (huijiaReq == null) {
            log.info("[x44,失败]模板消息2发送，json为空。");
            return;
        }

        WxTemplate wxtemplate = new WxTemplate();
        wxtemplate.setTouser(openId);
        wxtemplate.setTopcolor("#000000");
        wxtemplate.setTemplate_id(this.SUCCESSTEMPLATE);
        wxtemplate.addDataItem("first", "#0000FF", "您的业务回价已完成");
        wxtemplate.addDataItem("keyword1", "#0000FF", text.replace("{0}", huijiaReq.getMiniid()));
        wxtemplate.addDataItem("keyword2", "#0000FF", DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        String jsonString = JSONObject.fromObject(wxtemplate).toString();
        String access_token = this.weixinUtil.getAccessToken(this.APPID, this.APPSECRET);
        String url = this.SENDMESGTEMPLATE + access_token;
        log.info("[x45,发送]成功模板消息发送" + url);
        JSONObject jsonObject = this.weixinUtil.httpRequest(url, "POST", jsonString);
        huijiaReq.setStatus(HuijiaReqStatus.Message.flag);
        huijiaReq.setSuccesstime(new Date());
        this.huijiaReqDao.save(huijiaReq);
        int result = 0;
        if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            log.info(String.format("[x48,错误]成功模板消息发生异常:errcode:{%s} errmsg:{%s}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")));
        }
        log.info("[x49,成功]成功模板消息发送成功：" + result);
    }

    /**
     * 待处理通知模板推送
     *
     * @param vissid    vissId
     * @param text      文本
     * @param loginname 绑定账户
     */
    public void sendPendingMsg(String vissid, String text, String loginname) {
        log.info("[x71,准备]待处理通知模板发送。" + vissid);
        if (StringUtils.isEmptyOrNull(loginname)) {
            log.info("[x72,失败]待处理通知模板发送，loginname为空。");
            return;
        }
        String openId = this.userUtil.getOpenId(loginname);
        if (StringUtils.isEmptyOrNull(openId)) {
            log.info("[x73,失败]待处理通知模板发送，openId为空。");
            return;
        }
        WxTemplate wxtemplate = new WxTemplate();
        wxtemplate.setTouser(openId);
        wxtemplate.setTopcolor("#000000");
        wxtemplate.setTemplate_id(this.PENDINGMSG);
        wxtemplate.addDataItem("keyword1", "#0000FF", text);
        wxtemplate.addDataItem("keyword2", "#0000FF", "业务通知");
        wxtemplate.addDataItem("keyword3", "#0000FF", DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        String jsonString = JSONObject.fromObject(wxtemplate).toString();
        String access_token = this.weixinUtil.getAccessToken(this.APPID, this.APPSECRET);
        //		String access_token = "b0fl58PnQqMNV-Pr2ASewgDUb-DtG5_7c8YnsjDgNK40zQy5rbMHMdTYN2cQ4hknZeQVdqbL5epqopVhtcy_Ssbve4RwB40D1zFZJ8j_iN_iigsji-KmmccNRlXW5KymDERdCDARSF";
        String url = this.SENDMESGTEMPLATE + access_token;
        log.info("[x75,发送]待处理通知模板消息发送" + url);
        JSONObject jsonObject = this.weixinUtil.httpRequest(url, "POST", jsonString);
        int result = 0;
        if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            log.info(String.format("[x78,错误]待处理通知模板消息发生异常:errcode:{%s} errmsg:{%s}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")));
        }
        log.info("[x79,成功]待处理通知模板消息发送成功：" + result);
    }

    /**
     * 判断单据是否过期
     *
     * @param date 单据的过期时间
     * @return true 表示过期 false 表示不过期
     */
    private boolean isExpired(Date date) {
        return date.before(new Date());
    }

    // ----------------------------------------------------------------------------------
    // HuijiaReq 的 Service 功能
    private HuijiaReq queryOne(String param, String status, String miniid, String vissid) {
        Map<String, String> params = new HashMap<>();
        params.put("q|openid", param);
        params.put("q|status", status);
        if (StringUtils.isNotEmptyOrNull(miniid)) {
            params.put("q|miniid", miniid);
        }
        if (StringUtils.isNotEmptyOrNull(vissid)) {
            params.put("q|vissid", vissid);
        }
        Specification<HuijiaReq> spec = QueryListUtil.buildSpecification(params, HuijiaReq.class);
        List<HuijiaReq> list = this.springDataJpaBase.findAll(HuijiaReq.class, spec, new Sort(Direction.fromString("DESC"), "createtime"));
        if (list.size() > 1) {
            log.info("不正常情况，不应获取多个");
            return list.get(0);

        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            log.info("不正常情况，不应获取不到");
            return null;
        }
    }
}