package com.caad.wechat.utils.sdk;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpUtil {

    public static JSONObject postAndReturnJSONObject(String url, Map<String, Object> params, Map<String, Object> headers) throws Exception {
        try {
            String result = post(url, params, headers);
            return convertTextToObject(result);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static JSONObject postAndReturnJSONObject(String url, String content, Map<String, Object> headers) throws Exception {
        try {
            String result = post(url, content, headers);
            return convertTextToObject(result);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static JSONObject getAndReturnJSONObject(String url, Map<String, Object> params, Map<String, Object> headers) throws Exception {
        try {
            String result = get(url, params, headers);
            return convertTextToObject(result);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static void closeResource(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable == null) {
                continue;
            }
            try {
                closeable.close();
            } catch (Exception ex) {
                // NOTODO
            }
        }
    }

    public static JSONObject convertTextToObject(String content) {
        return JSONObject.fromObject(content);
    }

    // 创建httpclient连接池
    private static PoolingHttpClientConnectionManager httpClientConnectionManager = initHttpClient();

    /**
     * 连接超时
     */
    private static final int TIMEOUT_CONNECTION = 60000;
    /**
     * 读取超时
     */
    private static final int TIMEOUT_SOCKET = 60000;

    private static PoolingHttpClientConnectionManager initHttpClient() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();//创建httpclient连接池
        //httpClientConnectionManager.setMaxTotal(Constant.HTTPCLIENT_CONNECTION_COUNT);//设置连接池最大数量
        //httpClientConnectionManager.setDefaultMaxPerRoute(Constant.HTTPCLIENT_MAXPERROUTE_COUNT);//设置单个路由最大连接数量
        return httpClientConnectionManager;
    }

    //请求重试机制
    private static HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= 3) { // 超过三次则不再重试请求
                return false;
            }
            if (exception instanceof InterruptedIOException) { // Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) { // Unknown host
                return false;
            }
            if (exception instanceof ConnectTimeoutException) { // Connection refused
                return false;
            }
            if (exception instanceof SSLException) { // SSL handshake exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) { // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }
    };

    /**
     * 将InputStream 转化为String
     */
    private static String getStreamAsString(InputStream stream, String charset) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset), 8192);
            StringWriter writer = new StringWriter();

            char[] chars = new char[8192];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            return writer.toString();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            closeResource(stream);
        }
    }

    private static CloseableHttpClient getHttpClient(String url) throws Exception {
        if (url.startsWith("https://")) {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                    //信任所有
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                e.printStackTrace();
            }
            return HttpClients.createDefault();
        } else {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT_CONNECTION).setSocketTimeout(TIMEOUT_SOCKET).setCookieSpec(CookieSpecs.DEFAULT).build(); // 创建全局的requestConfig
            LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy(); // 声明重定向策略对象
            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).setDefaultRequestConfig(requestConfig).setRedirectStrategy(redirectStrategy).setRetryHandler(myRetryHandler).build();
            return httpClient;
        }
    }

    /**
     * 发起网络请求。GET
     */
    public static String get(String url, Map<String, Object> params, Map<String, Object> headers) throws Exception {
        try {
            StringBuffer sb = new StringBuffer(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(String.format("%s%s=%s", sb.indexOf("?") == -1 ? "?" : "&", URLEncoder.encode(entry.getKey(), "UTF-8"), URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8")));
            }
            url = sb.toString();
            HttpGet httpGet = new HttpGet(url);
            HttpClient httpClient = getHttpClient(url);

            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
            }
            InputStream is = httpResponse.getEntity().getContent();
            return getStreamAsString(is, "UTF-8");
        } catch (UnsupportedEncodingException | ClientProtocolException e) {// 发生致命的异常，可能是协议不对或者返回的内容有问题
            throw new Exception(e);
        }
    }

    /**
     * 发起网络请求。POST
     */
    public static String post(String url, Map<String, Object> params, Map<String, Object> headers) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(JSONObject.fromObject(params).toString(), "UTF-8");//解决中文乱码问题
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // 设置HTTP POST头部参数
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                if (entry.getKey().equals("Content-Type")) {
                    continue;
                }
                httpPost.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }

            // HTTP POST请求
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
            }

            // 获取HTTP POST的返回值
            InputStream is = httpResponse.getEntity().getContent();
            return getStreamAsString(is, "UTF-8");
        } catch (UnsupportedEncodingException | ClientProtocolException e) {// 发生致命的异常，可能是协议不对或者返回的内容有问题
            throw new Exception(e);
        }
    }

    /**
     * 发起网络请求。POSTCONTENT
     */
    public static String post(String url, String content, Map<String, Object> headers) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = getHttpClient(url);
        try {
            ;
            httpPost.setEntity(new StringEntity(content, "UTF-8")); // 设置HTTP POST请求参数
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
            }
            InputStream is = httpResponse.getEntity().getContent();
            return getStreamAsString(is, "UTF-8");
        } catch (UnsupportedEncodingException | ClientProtocolException e) {// 发生致命的异常，可能是协议不对或者返回的内容有问题
            throw new Exception(e);
        }
    }
}
