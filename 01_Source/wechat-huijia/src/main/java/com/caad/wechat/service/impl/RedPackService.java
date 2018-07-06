package com.caad.wechat.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.caad.wechat.dao.RedPackQueryReqDao;
import com.caad.wechat.dao.RedPackSendReqDao;
import com.caad.wechat.entity.RedPackQueryReq;
import com.caad.wechat.entity.RedPackQueryResp;
import com.caad.wechat.entity.RedPackQueryRespItem;
import com.caad.wechat.entity.RedPackSendReq;
import com.caad.wechat.entity.RedPackSendResp;
import com.caad.wechat.entity.RedPackSendRespItem;
import com.caad.wechat.enums.RedPackStatus;
import com.caad.wechat.model.OrderqueryEntity;
import com.caad.wechat.model.RedPackEntity;
import com.caad.wechat.util.EncryptUtil;
import com.caad.wechat.util.MessageUtil;
import com.caad.wechat.util.RedPackUtil;
import com.caad.wechat.util.UserUtil;
import com.caadt.cln.common.util.DateUtil;

import net.sf.json.JSONObject;

@Service("SenRedPackService")
public class RedPackService {

    private static Log log = LogFactory.getLog(RedPackService.class);

    private static final String NICK_NAME = "中估联信息";// 提供方名称

    private static final String SEND_NAME = "中估联信息";// 商户名称

    private static final String WISHING = "您的奖金提现,请查收";// 红包祝福语

    private static final String ACT_NAME = "回价奖金";// 活动名称

    private static final String REMARK = "提现后请访问个人账户查询余额";// 备注

    private static final String TOTAL_NUM = "1";// 红包发放总人数

    private static String MCH_CER = "apiclient_cert.p12";// 商户认证证书

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private RedPackQueryReqDao redPackQueryReqDao;

    @Autowired
    private RedPackSendReqDao redPackSendReqDao;

    @Value("#{settings['wechat.appid']}") // AppID(应用ID)
    private String APPID;

    @Value("#{settings['wechat.appsecret']}") // AppSecret(应用密钥)
    private String APPSECRET;

    @Value("#{settings['merchant.id']}") // 商户号
    private String MCH_ID;

    @Value("#{settings['merchant.pay.secretkey']}") // 商户支付密钥
    private String MCH_KEY;

    @Value("#{settings['merchant.pay.url']}") // 商户支付接口
    private String MCH_PAY_URL;

    @Value("#{settings['huijia.client.ip']}") // IP地址(上线为服务器地址)
    private String CLIENT_IP;

    @Value("#{settings['merchant.order.query.url']}") // 订单查询接口
    private String MCH_ORDER_QUERY_URL;

    /**
     * 发红包接口
     */
    public RedPackSendResp sendRedPackByEncryptStr(String requestStr) {
        try {
            RedPackSendReq redPackSendReq = new RedPackSendReq();
            redPackSendReq.setEncryptstr(requestStr);
            log.info("[x50,收到]调用发送红包接口5");
            JSONObject json = EncryptUtil.decrypt(requestStr);// 对传递过来的数据进行解密
            redPackSendReq.setDecryptstr(json.toString());
            this.redPackSendReqDao.save(redPackSendReq);
            List<RedPackSendRespItem> result = sendRedPack(this.userUtil.formatLoginName(json.getString("LoginName")), json.getString("Amounts"), redPackSendReq);
            return new RedPackSendResp("SUCCESS", "连接成功", result);
        } catch (Exception e) {
            log.info("[x51,异常]调用发送红包接口异常" + e.getMessage());
        }
        return new RedPackSendResp("FAIL", "连接失败", null);
    }

    /**
     * 查询红包接口
     */
    public RedPackQueryResp queryRedPackInfoByEncryptStr(String requestStr) {
        try {
            RedPackQueryReq redPackQueryReq = new RedPackQueryReq();
            redPackQueryReq.setEncryptstr(requestStr);
            log.info("[x60,收到]调用查询订单接口6");
            JSONObject json = EncryptUtil.decrypt(requestStr);// 对传递过来的数据进行解密
            redPackQueryReq.setDecryptstr(json.toString());
            this.redPackQueryReqDao.save(redPackQueryReq);
            RedPackQueryRespItem result = queryRedPackInfo(json.getString("TipId"), redPackQueryReq);
            return new RedPackQueryResp("SUCCESS", "连接成功", result);
        } catch (Exception e) {
            log.info("[x61,异常]调用查询红包订单接口异常" + e.getMessage());
        }
        return new RedPackQueryResp("FAIL", "连接失败", null);
    }

    /**
     * 调用微信API发送红包
     */
    public List<RedPackSendRespItem> sendRedPack(String loginname, String total_amount, RedPackSendReq redPackSendReq) {
        List<RedPackSendRespItem> item = new ArrayList<>();
        log.info("[x53,开始]保存数据至数据库"); // 请求数据写入数据库
        redPackSendReq.setLoginname(loginname);
        redPackSendReq.setOpendid(this.userUtil.getOpenId(loginname));
        redPackSendReq.setTotalamount(total_amount);
        redPackSendReq.setCreatetime(new Date());

        double total = Double.parseDouble(total_amount) * 100;// 拆分红包
        while (total > 0) {
            double num = 0;
            if (total > 20000) {// 发一次200
                num = 20000;
                total -= 20000;
            } else if (total > 0) {// 发一次total
                num = total;
                total = 0;
            }
            RedPackEntity redPackPo = new RedPackEntity();// 开始发，发num元红包
            redPackPo.setNonce_str(UUID.randomUUID().toString().replace("-", ""));// 随机字符串，不长于32位,获取UUID作为随机字符串
            redPackPo.setMch_billno(this.MCH_ID + new SimpleDateFormat("yyyyMMdd").format(new Date()) + RedPackUtil.genRandomStr(10));// 商户订单号
            redPackPo.setMch_id(this.MCH_ID);// 商户号
            redPackPo.setWxappid(this.APPID);// 公众账号APPID
            redPackPo.setNick_name(NICK_NAME);// 提供方名称
            redPackPo.setSend_name(SEND_NAME);// 商户名称
            redPackPo.setRe_openid(this.userUtil.getOpenId(loginname));// 用户OPENID
            redPackPo.setTotal_amount((int) num + "");// 付款金额
            // redPackPo.setMin_value("100");// 最小红包金额
            // redPackPo.setMax_value("20000");// 最大红包金额
            redPackPo.setTotal_num(TOTAL_NUM);// 红包发放总人数
            redPackPo.setWishing(WISHING);// 红包祝福语
            redPackPo.setClient_ip(this.CLIENT_IP); // IP地址
            redPackPo.setAct_name(ACT_NAME);// 活动名称
            redPackPo.setRemark(REMARK);// 备注

            Map<String, String> sParaTemp = new HashMap<>();// 把请求参数打包成数组
            sParaTemp.put("nonce_str", redPackPo.getNonce_str());
            sParaTemp.put("mch_billno", redPackPo.getMch_billno());
            sParaTemp.put("mch_id", redPackPo.getMch_id());
            sParaTemp.put("wxappid", redPackPo.getWxappid());
            sParaTemp.put("nick_name", redPackPo.getNick_name());
            sParaTemp.put("send_name", redPackPo.getSend_name());
            sParaTemp.put("re_openid", redPackPo.getRe_openid());
            sParaTemp.put("total_amount", redPackPo.getTotal_amount());
            // sParaTemp.put("min_value", redPackPo.getMin_value());
            // sParaTemp.put("max_value", redPackPo.getMax_value());
            sParaTemp.put("total_num", redPackPo.getTotal_num());
            sParaTemp.put("wishing", redPackPo.getWishing());
            sParaTemp.put("client_ip", redPackPo.getClient_ip());
            sParaTemp.put("act_name", redPackPo.getAct_name());
            sParaTemp.put("remark", redPackPo.getRemark());
            redPackPo.setSign(this.getSign(sParaTemp));
            log.info("[x55,发送]红包开始发送:" + JSONObject.fromObject(redPackPo));
            Map<String, String> result;
            try {
                result = doResult(redPackPo, this.MCH_PAY_URL);// 发送红包接口
                log.info("[x56,成功]发送红包完成:" + result);
                RedPackSendRespItem redPackSendRespItem = new RedPackSendRespItem();// 返回封装后的数据
                JSONObject jsonObject = JSONObject.fromObject(result);
                redPackSendRespItem.setAmounts(num / 100 + "");
                redPackSendRespItem.setErrCode(jsonObject.getString("err_code"));
                redPackSendRespItem.setErrMsg(jsonObject.getString("err_code_des"));
                redPackSendRespItem.setLoginName(this.userUtil.getLoginName(jsonObject.getString("re_openid")));
                redPackSendRespItem.setReturnCode(jsonObject.getString("err_code"));
                redPackSendRespItem.setReturnMsg(jsonObject.getString("return_msg"));
                redPackSendRespItem.setSendTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
                redPackSendRespItem.setStatus(RedPackStatus.Sent.flag);
                redPackSendRespItem.setTipId(redPackPo.getMch_billno());
                redPackSendReq.setResponsestr(JSONObject.fromObject(redPackSendRespItem).toString());
                this.redPackSendReqDao.save(redPackSendReq);
                item.add(redPackSendRespItem);
            } catch (KeyManagementException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | DocumentException e) {
                log.info("[x59,失败]红包发送发生异常" + e.getMessage());
                return null;
            }
        }
        return item;
    }

    /**
     * 调用微信API查询红包状态
     */
    public RedPackQueryRespItem queryRedPackInfo(String mch_billno, RedPackQueryReq redPackQueryReq) {
        log.info("[x63,开始]保存数据至数据库");// 请求数据写入数据库
        redPackQueryReq.setMchbillno(mch_billno);
        redPackQueryReq.setCreatetime(new Date());

        OrderqueryEntity queryEntity = new OrderqueryEntity();
        queryEntity.setAppid(this.APPID);// 应用APPID
        queryEntity.setMch_id(this.MCH_ID);// 商户号
        queryEntity.setMch_billno(mch_billno);// 商户订单号
        queryEntity.setBill_type("MCHT");// 订单类型，通过商户订单号获取红包信息。
        queryEntity.setNonce_str(UUID.randomUUID().toString().replace("-", ""));// 随机字符串

        Map<String, String> temp = new HashMap<>();// 把请求参数打包成数组
        temp.put("appid", queryEntity.getAppid());
        temp.put("mch_id", queryEntity.getMch_id());
        temp.put("nonce_str", queryEntity.getNonce_str());
        temp.put("mch_billno", queryEntity.getMch_billno());
        temp.put("bill_type", queryEntity.getBill_type());
        queryEntity.setSign(this.getSign(temp));
        log.info("[x65,发送]查询红包订单开始：" + JSONObject.fromObject(queryEntity));
        Map<String, String> result;
        try {
            result = doResult(queryEntity, this.MCH_ORDER_QUERY_URL);// 查询红包接口
            log.info("[x67,成功]查询红包订单完成:" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            RedPackQueryRespItem redPackQueryRespItem = new RedPackQueryRespItem();
            redPackQueryRespItem.setTipId(jsonObject.getString("mch_billno"));
            if (jsonObject.containsKey("status")) {// 状态
                for (RedPackStatus status : RedPackStatus.values()) {
                    if (status.ename.equals(jsonObject.getString("status"))) {
                        log.info("当前订单状态：" + status.flag);
                        redPackQueryRespItem.setStatus(status.flag);
                    }
                }
                redPackQueryReq.setResponsestr(JSONObject.fromObject(redPackQueryRespItem).toString());
                this.redPackQueryReqDao.save(redPackQueryReq);
                return redPackQueryRespItem;
            } else {
                return null;//如果返回状态不存在，则直接返回空
            }
        } catch (KeyManagementException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | DocumentException e) {
            log.info("[x69,失败]查询红包订单发生异常" + e);
            return null;
        }
    }

    /**
     * 公用方法：生成数字签名，用于调用微信接口
     */
    private String getSign(Map<String, String> sParaTemp) {
        Map<String, String> sPara = RedPackUtil.paraFilter(sParaTemp);// 除去数组中的空值和签名参数
        String prestr = RedPackUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String key = "&key=" + this.MCH_KEY; // 商户支付密钥
        String mysign = RedPackUtil.genSign(prestr, key);
        log.info("当前生成的签名：" + mysign);
        return mysign;
    }

    /**
     * 公用方法：向微信发送请求
     */
    private Map<String, String> doResult(Object object, String url) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, DocumentException {
        String re_openid = null;
        if (object instanceof RedPackEntity) {
            RedPackEntity redPackEntity = (RedPackEntity) object;
            re_openid = redPackEntity.getRe_openid();
        }
        String respXml = MessageUtil.messageToXml(object);
        respXml = respXml.replace("__", "_");
        if (re_openid != null && re_openid.contains("__")) {
            String rp_openid = re_openid.replace("__", "_");
            respXml = respXml.replace(rp_openid, re_openid);
        }
        Map<String, String> map = new HashMap<>();
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream instream = RedPackService.class.getClassLoader().getResourceAsStream(MCH_CER)) {
            keyStore.load(instream, this.MCH_ID.toCharArray());
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, this.MCH_ID.toCharArray()).build();
        // 只允许TLSv1协议
        @SuppressWarnings("deprecation")
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {

            HttpPost httpPost = new HttpPost(url);

            StringEntity reqEntity = new StringEntity(respXml, "UTF-8");

            reqEntity.setContentType("application/x-www-form-urlencoded");// 设置类型

            httpPost.setEntity(reqEntity);

            log.info("发送请求中:" + httpPost.getRequestLine());

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                log.info("返回状态码:" + response.getStatusLine());
                if (entity != null) {
                    InputStream inputStream = entity.getContent();// 从request中取得输入流
                    SAXReader reader = new SAXReader();// 读取输入流
                    Document document = reader.read(inputStream);// 得到xml根元素
                    Element root = document.getRootElement();// 得到根元素的所有子节点
                    @SuppressWarnings("unchecked")
                    List<Element> elementList = root.elements();
                    for (Element e : elementList) {
                        map.put(e.getName(), e.getText());
                    }
                    inputStream.close();// 释放资源
                }
                EntityUtils.consume(entity);
            }
        }
        return map;
    }
}
