package com.caad.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.service.impl.SendMsgService;
import com.caad.wechat.util.UserUtil;
import com.caadt.cln.common.util.EncryptAES;
import com.caadt.cln.common.util.FileUtil;
import com.caadt.cln.common.util.HttpClientTemplate;
import com.caadt.cln.common.util.HttpUtil;
import com.caadt.cln.common.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InnerAPIControllerTest {

    @Autowired
    private HttpUtil httpClientTemplateToDataSso;

    @Autowired
    private UserUtil UserUtil;

    @Autowired
    private HttpUtil httpClientTemplateToDataConfirm;

    private static Log log = LogFactory.getLog(InnerAPIControllerTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        String url = "http://localhost:8080/wechat-huijia/innerdataapi/sendAppraisalTaskInputInfo.do";
        httpClientTemplate.setUrl(url);
        httpClientTemplate.setMethod("POSTCONTENT");
        httpClientTemplate.setPostcontent(FileUtil.getContentStringInClasspath(this.getClass(), "data1.json"));
        String str = httpClientTemplate.invoke(new HashMap<>());
        log.info(str);
    }

    @Test
    public void test2() {
        String url = "http://172.16.2.3:5001/Dispacther/DispactherQuickAdvisor/Assessment";
        Map<String, String> params = new HashMap<>();
        params.put("id", "1601281501126918437");
        params.put("text", "X012615000167-333333");
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        httpClientTemplate.setUrl(url);
        httpClientTemplate.setMethod("POST");
        String result = httpClientTemplate.invoke(params);
        log.info(result);

    }

    @Test
    public void test3() {
        Map<String, String> params = new HashMap<>();
        String json = FileUtil.getContentStringInClasspath(this.getClass(), "data2.json");
        params.put("json", json);
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        String url = "http://172.16.2.104/wechat-huijia/innerdataapi/sendAppraisalTaskResult.do";
        httpClientTemplate.setUrl(url);
        httpClientTemplate.setMethod("POST");
        String str = httpClientTemplate.invoke(params);
        log.info(str);
    }

    @Test
    public void test4() {
        Map<String, String> params = new HashMap<>();
        String json = FileUtil.getContentStringInClasspath(this.getClass(), "data1.json");
        params.put("json", json);
        params.put("singlePrice", "8983");
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        String url = "http://127.0.0.1:8080/wechat-huijia/hello.do";
        httpClientTemplate.setUrl(url);
        httpClientTemplate.setMethod("POST");
        String str = httpClientTemplate.invoke(params);
        log.info(str);

    }

    @Test
    public void test6() {
        Map<String, String> params = new HashMap<>();
        String passwordEncrypted = "";
        try {
            passwordEncrypted = EncryptAES.encrypt("yangxiaoli123", "4CB85BBA862A4234B226897A1ED92E70");
            log.info(passwordEncrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("username", "yangxiaoli");
        params.put("pwd", passwordEncrypted);
        String str = httpClientTemplateToDataSso.invoke(params);
        log.info(str);

    }

    @Test
    public void test7() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1114694260040000300");
        params.put("text", "K01261607000110-18200");
        String result = httpClientTemplateToDataConfirm.invoke(params);
        log.info(result);

    }

    /**
     * 将字符串以字节码形式按charset格式转换为新的字符串
     */
    public String fromBASE64(String str, String charset) {
        if (StringUtils.isEmptyOrNull(str))
            return "";
        try {
            return new String(Base64.encodeBase64(str.getBytes("UTF-8")));

        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Test
    public void test5() {
        SendMsgService sendMsg = new SendMsgService();
        sendMsg.onReceiveTextMsg("", "");
    }

    @Test
    public void test10() {
        boolean sso = UserUtil.checkLoginNamePswdViaSSO("xitong", "xitong123");
        log.info(sso);
    }
}
