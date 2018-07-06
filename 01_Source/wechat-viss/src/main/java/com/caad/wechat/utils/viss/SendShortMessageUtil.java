package com.caad.wechat.utils.viss;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

public class SendShortMessageUtil {

    private static Log log = LogFactory.getLog(SendShortMessageUtil.class);

    /**
     * 发送短信
     *
     * @param moblieNumer 手机号码
     * @param msg         发送消息内容
     * @return 状态
     * @throws IOException
     */
    public static String sendShortMessage(String moblieNumer, String msg, String single) throws IOException {
        String Url = "http://sh2.cshxsp.com/sms.aspx?action=send";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(Url);
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        NameValuePair userid = new NameValuePair("userid", "Caad");
        NameValuePair account = new NameValuePair("account", "jksc749");
        NameValuePair password = new NameValuePair("password", "a123654");
        NameValuePair mobile = new NameValuePair("mobile", moblieNumer);
        NameValuePair content = new NameValuePair("content", "【" + single + "】" + msg);
        NameValuePair sendTime = new NameValuePair("sendTime", "");
//		NameValuePair extno = new NameValuePair("extno", "");
        post.setRequestBody(new NameValuePair[]{userid, account, password, mobile, content, sendTime});
        int statu = client.executeMethod(post);
        String str = post.getResponseBodyAsString();
        String returnstatus = "";
        try {
            //将字符转化为XML
            Document doc = DocumentHelper.parseText(str);
            //获取根节点
            Element rootElt = doc.getRootElement();
            //获取根节点下的子节点的值
            returnstatus = rootElt.elementText("returnstatus").trim();
            String message = rootElt.elementText("message").trim();
            String remainpoint = rootElt.elementText("remainpoint").trim();
            String taskID = rootElt.elementText("taskID").trim();
            String successCounts = rootElt.elementText("successCounts").trim();
            log.info("返回状态为：" + returnstatus);
            log.info("返回信息提示：" + message);
            log.info("返回余额：" + remainpoint);
            log.info("返回任务批次：" + taskID);
            log.info("返回成功条数：" + successCounts);
            return returnstatus;
        } catch (Exception e) {
            log.error(e);
        }
        return returnstatus;
    }
}