package test.com.caad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.util.WeixinUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MenuTest {

    @Autowired
    private WeixinUtil weixinUtil;

    // AppID(应用ID)
    @Value("#{settings['wechat.appid']}")
    private String APPID;

    // AppSecret(应用密钥)
    @Value("#{settings['wechat.appsecret']}")
    private String APPSECRET;

    private final static String URL = "http://wangguan.visscaa.com/huijia/createMenu/getOpenId.do";

    private static Log log = LogFactory.getLog(MenuTest.class);

    /**
     * 自定义创建菜单
     */
    @Test
    public void createMenuTest() {
        String menu = "{\"button\":[{\"type\":\"view\",\"name\":\"获取当前用户信息\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid="//
                + APPID//
                + "&redirect_uri="//
                + URL//
                + "&response_type="//
                + "code"//
                + "&scope="//
                + "snsapi_userinfo"//
                + "&state="//
                + "1#wechat_redirect\""//
                + "}]}";//
        String accessToken = "CDEaNnjcOUUymxHjEmpaOzj9w_zEii5dwUqwpNhoJ7gevh7rq9cZDLIfJmpbmSF3ceAsrFeL3c6N2D9UDoOywZtNgJ1cMLaK83F7TNavnHPEg_1MZb64aFcKlGf_1DXYUIKfADAGUW";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        weixinUtil.httpRequest(url, "POST", menu);
    }

    /**
     * click事件创建菜单
     */
    @Test
    public void createClickMenu() {
        String menu = "{\"button\":[{\"type\":\"click\",\"name\":\"我的账户\",\"key\":\"MYACCOUNT\"}]}";
        //		String accessToken = weixinUtil.getAccessToken(APPID, APPSECRET);
        String accessToken = "OaNnqVktJPy4NXu73yvV3p3Dgr4_HlxoqcMuF8kOiBnfHK5lHyu2OgLLL4L54WcUB9g5lBZv-6nqzzJjKrCg9gBEWDt4RNRAuLcytCztP1gXKDjAAAMZS";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        weixinUtil.httpRequest(url, "POST", menu);
    }

    /**
     * view事件创建菜单
     */
    @Test
    public void createViewMenu() {
        String menu = "{\"button\":[{\"type\":\"view\",\"name\":\"我的账户\",\"url\":\"http://www.baidu.com\"}]}";
        String accessToken = weixinUtil.getAccessToken(APPID, APPSECRET);
        if (accessToken != null) {
            String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
            weixinUtil.httpRequest(url, "POST", menu);
        }
    }

    /**
     * 删除菜单
     */
    @Test
    public void deleteMenuTest() {
        String accessToken = "zRhu7r7nUSi6mRIe6bg0QUMAgW1LIg6jbtM7jQgSYYZ4drYLh_Bg7eMNqwXKozGtFg-1kuoXN_N6s6_veuRliRxs7a9iwNgSfAbGi8p17EVbcaacZrvXmiKgwMOHZRxYSHCdCIAHWR";
        log.info(accessToken);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
        weixinUtil.httpRequest(url, "GET", null);
    }
}
