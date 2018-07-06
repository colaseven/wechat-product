package com.caad.wechat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caadt.cln.common.util.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caad.wechat.service.impl.SendMsgService;
import com.caad.wechat.util.SignUtil;
import com.caadt.cln.common.util.StringUtils;

@Controller
public class CaadController {

    private static Log log = LogFactory.getLog(CaadController.class);

    @Autowired
    private SendMsgService sendMsg;

    /**
     * 确认请求来自微信服务器
     */
    @RequestMapping("/CaadServlet")
    @ResponseBody
    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(String.format("%s,%s处理中……", request.getMethod(), request.getRequestURI()));
        switch (request.getMethod()) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * 验证。
     */
    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        PrintWriter out = response.getWriter();

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
    }

    /**
     * 处理微信服务器发来的用户消息、菜单事件。
     */
    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = parseXmlToMap(request.getInputStream());// 从request中取得输入流，按xml解析
        String msgType = map.get("MsgType");// 消息类型
        String openId = map.get("FromUserName");// 发送方帐号（open_id）
        String toUserName = map.get("ToUserName");// 公众帐号
        String text = StringUtils.dealEmpty(map.get("Content"), map.get("EventKey")); // 消息文本，菜单KEY
        String result = sendMsg.onReceiveTextMsg(text, openId); // 消息的接收、处理、响应
        //
        String respXml = StringUtils.isEmptyOrNull(result) ? "" : sendMsg.toTextMsgXml(result, openId, msgType, toUserName); // 调用核心业务类接收消息、处理消息
        PrintWriter out = response.getWriter(); // 响应消息
        out.print(respXml);
        out.close();
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> parseXmlToMap(InputStream inputstream) {
        Map<String, String> map = new HashMap<>();// 将解析结果存储在HashMap中
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputstream);// 读取输入流
            Element root = document.getRootElement();// 得到xml根元素
            List<Element> elements = root.elements();// 得到根元素的所有子节点
            for (Element element : elements) {
                map.put(element.getName(), element.getText());// 遍历所有子节点
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputstream);
        }
        log.info("收到的文本消息进行xml解析：" + String.valueOf(map));
        return map;
    }
}
