package com.caad.wechat;

import com.caad.wechat.utils.wechat.AuthorizationUtil;
import com.caad.wechat.utils.wechat.WechatUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WechatTest {

    private static Log log = LogFactory.getLog(WechatTest.class);

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Autowired
    private WechatUtil wechatUtil;


    @Test
    public void getOpenId() throws Exception {
        String openId = authorizationUtil.getOpenId("", "wx4274ce8a60e68b5d", "2f072993a2adf08a5edbc593b7b56191");
        log.info(openId);
    }


    @Test
    public void getAccessToken() {
        String accessToken = wechatUtil.getAccessToken("wxe643becc64502702", "3d00326272bfe1cddadb98317bfe2f7e");
        log.info(accessToken);
    }


    @Test
    public void getUserInfo() {
        String accessToken = wechatUtil.getAccessToken("wxe643becc64502702", "3d00326272bfe1cddadb98317bfe2f7e");
        JSONObject wechatUserInfo = wechatUtil.getWechatUserInfo(accessToken, "oh9Bzv7_coMVgmwDILldizv0gmjk");
        log.info(wechatUserInfo.toString());
    }


    @Test
    public void tokenExpires() {
        long current_time=1514366088232L;
        long lasttime=1510325053723L;
        long tokenTime=7200L;
        System.out.print((current_time - lasttime)/100);
        System.out.print((current_time - lasttime) /100 <= tokenTime);
    }
}
