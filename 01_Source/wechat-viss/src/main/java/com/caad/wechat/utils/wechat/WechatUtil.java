package com.caad.wechat.utils.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.dao.WechatAccessTokenDao;
import com.caad.wechat.entity.WechatAccessToken;
import com.caad.wechat.utils.viss.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信公众平台通用接口工具类
 */
@Component("wechatUtil")
public class WechatUtil {


    @Autowired
    private WechatAccessTokenDao wechatAccessTokenDao;

    private static Log log = LogFactory.getLog(WechatUtil.class);


    /**
     * 获取access_token
     *
     * @param appId     凭证
     * @param appSecret 密钥
     * @return accessToken
     */
    public String getAccessToken(String appId, String appSecret) {
        WechatAccessToken wechatAccessToken = wechatAccessTokenDao.findByAppId(appId);
        if (StringUtils.isEmptyOrNull(wechatAccessToken)) {
            String accessTokenUrl = WechatConstant.ACCESS_TOKEN_URL;
            String requestUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            WechatAccessToken token = new WechatAccessToken();
            token.setAppId(appId);
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInt("expires_in"));
            token.setLast_time(System.currentTimeMillis());
            return wechatAccessTokenDao.save(token).getAccess_token();
        }
        // 如果token时间超时，重新获取
        long current_time = System.currentTimeMillis();
        if ((current_time - wechatAccessToken.getLast_time()) / 1000 >= wechatAccessToken.getExpires_in()) {
            String accessTokenUrl = WechatConstant.ACCESS_TOKEN_URL;
            String requestUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            wechatAccessToken.setExpires_in(jsonObject.getInt("expires_in"));
            wechatAccessToken.setAccess_token(jsonObject.getString("access_token"));
            wechatAccessToken.setLast_time(System.currentTimeMillis());
            WechatAccessToken token = wechatAccessTokenDao.save(wechatAccessToken);
            return token.getAccess_token();
        }
        return wechatAccessToken.getAccess_token();
    }

    /**
     * 获取微信用户信息
     *
     * @param accessToken 微信token
     * @param openId      微信用户id
     * @return 微信用户信息
     */
    public JSONObject getWechatUserInfo(String accessToken, String openId) {
        String wechatUserInfoUrl = WechatConstant.WECHAT_USERINFO_URL;
        String requestUrl = wechatUserInfoUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        return httpRequest(requestUrl, "GET", null);
    }

    /**
     * 发起https请求并获取结果
     *
     * @param strUrl    请求地址
     * @param strMethod 请求方式（GET、POST）
     * @param content   提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public JSONObject httpRequest(String strUrl, String strMethod, String content) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory(); // 从上述SSLContext对象中得到SSLSocketFactory对象

            URL url = new URL(strUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(ssf);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestMethod(strMethod); // 设置请求方式（GET/POST）

            if ("GET".equalsIgnoreCase(strMethod)) {
                httpsURLConnection.connect();
            }
            if (null != content) { // 当有数据需要提交时
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(content.getBytes("UTF-8"));// 注意编码格式，防止中文乱码
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsURLConnection.disconnect();
            //
            String result = sb.toString();
            log.info(result);
            jsonObject = JSONObject.fromObject(result);
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https 请求出错", e);
        }
        return jsonObject;
    }
}
