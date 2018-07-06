package com.caad.wechat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * 微信公众平台通用接口工具类
 */
@Component("weixinUtil")
public class WeixinUtil {

    private static Log log = LogFactory.getLog(WeixinUtil.class);

    /**
     * 获取access_token的接口地址（GET）
     */
    @Value("#{settings['wechat.accesstokenurl']}")
    private String ACCESS_TOKEN_URL;

    /**
     * access_token的保存文件名
     */
    private static String FileName = "weixinToken.properties";

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return accessToken
     */
    public synchronized String getAccessToken(String appid, String appsecret) {
        try {
            // 文件获取token值及时间
            Properties prop = new Properties();// 属性集合对象
            InputStream fis = WeixinUtil.class.getClassLoader().getResourceAsStream(FileName);
            prop.load(fis);// 将属性文件流装载到Properties对象中
            fis.close();// 关闭流
            String access_token = prop.getProperty("access_token");
            log.info("从文件中读取的access_token值为：" + access_token);
            String expires_in = prop.getProperty("expires_in");
            String last_time = prop.getProperty("last_time");
            int int_expires_in = 0;
            long long_last_time = 0;

            try {
                int_expires_in = Integer.parseInt(expires_in);
                long_last_time = Long.parseLong(last_time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long current_time = System.currentTimeMillis();
            // 如果token时间超时，重新获取
            if ((current_time - long_last_time) / 1000 >= int_expires_in) {
                String requestUrl = this.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
                JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
                String j_access_token = (String) jsonObject.get("access_token");
                log.info("重新获取的access_token值为：" + j_access_token);
                Integer j_expires_in = (Integer) jsonObject.get("expires_in");
                // 保存
                if (j_access_token != null && j_expires_in != null) {
                    prop.setProperty("access_token", j_access_token);
                    prop.setProperty("expires_in", j_expires_in + "");
                    prop.setProperty("last_time", System.currentTimeMillis() + "");

                    URL url_ = WeixinUtil.class.getClassLoader().getResource(FileName);
                    log.info("access_token文件目录为：" + url_);
                    FileOutputStream fos = new FileOutputStream(new File(url_.toURI()));
                    prop.store(fos, null);
                    fos.close();// 关闭流
                }
                return j_access_token;
            } else {
                return access_token;
            }
        } catch (Exception e) {
            return null;
        }

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
