package test.com.caad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;

import com.caadt.cln.common.util.HttpClientTemplate;

public class HttpPostTest {

    private static Log log = LogFactory.getLog(HttpPostTest.class);

//    private static String getXmlInfo() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(" <xml>");
//        sb.append(" <ToUserName><![CDATA[CAAD2016]]></ToUserName>");
//        sb.append(" <FromUserName><![CDATA[oq8FEw79L2YeMF9G6pObe67Uy3dE]]></FromUserName> ");
//        sb.append(" <CreateTime>1348831860</CreateTime>");
//        sb.append(" <MsgType><![CDATA[text]]></MsgType>");
//        sb.append(" <Content><![CDATA[this is a test]]></Content>");
//        sb.append(" <MsgId>1234567890123456</MsgId>");
//        sb.append(" </xml>");
//        return sb.toString();
//    }


    public static void main(String[] args) throws ClientProtocolException, IOException {
        // 创建HttpClient实例     
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        httpClientTemplate.setUrl("http://127.0.0.1:8080/wechat-huijia/validate.do");
        httpClientTemplate.setMethod("POST");
        Map<String, String> param = new HashMap<String, String>();
        param.put("singlePrice", "8983");
        param.put("cityCode", "型");
        param.put("signature", "型");
        String result = httpClientTemplate.invoke(param);
        log.info(result);

//        HttpResponse response = httpclient.execute(httpgets);    
//        HttpEntity entity = response.getEntity();    
//        if (entity != null) {    
//            InputStream instreams = entity.getContent();    
//            String str = convertStreamToString(instreams);  
////            log.info("Do something");   
//            log.info(str);  
//            // Do not need the rest    
//            httpgets.abort();    
//        }  
//        HttpResponse response = httpclient.execute(httpgets);    
//        HttpEntity entity = response.getEntity();    
//        if (entity != null) {    
//            InputStream instreams = entity.getContent();    
//            String str = convertStreamToString(instreams);  
////            log.info("Do something");   
//            log.info(str);  
//            // Do not need the rest    
//            httpgets.abort();    
//        }  
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}