package com.caad.wechat.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caad.wechat.entity.RedPackQueryResp;
import com.caad.wechat.entity.RedPackSendResp;
import com.caad.wechat.model.caad.ResultTemplateEntity;
import com.caad.wechat.service.impl.RedPackService;
import com.caad.wechat.service.impl.SendMsgService;
import com.caad.wechat.util.UserUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/innerdataapi")
public class InnerAPIController {

    private static Log log = LogFactory.getLog(InnerAPIController.class);

    @Autowired
    private SendMsgService sendMsgService;

    @Autowired
    private RedPackService redPackService;

    @Autowired
    private UserUtil userUtil;

    /**
     * 接口1： 发送待回价模板信息
     */

    @RequestMapping(value = "/sendAppraisalTaskInputInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplateEntity sendAppraisalTaskInputInfo(@RequestBody JSONObject jsonObj) {
        log.info("[x10,收到]调用发送模板信息1接口" + String.valueOf(jsonObj));
        this.sendMsgService.sendTemplateMessage(jsonObj, userUtil.formatLoginName(jsonObj.getString("LoginName")));
        return new ResultTemplateEntity("Yes", "连接成功");
    }

    /**
     * 接口3： 发送成功模板信息
     */
    @RequestMapping(value = "/sendTextMsg", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplateEntity sendTextMsg(@RequestBody JSONObject jsonObj) {
        log.info("[x40,收到]调用发送模板信息2接口" + String.valueOf(jsonObj));
        this.sendMsgService.sendSuccessMessage(jsonObj.getString("Id"), jsonObj.getString("Text"), userUtil.formatLoginName(jsonObj.getString("LoginName")));
        return new ResultTemplateEntity("Yes", "连接成功");
    }

    /**
     * 接口6： 待处理通知
     */
    @RequestMapping(value = "/sendPendingMsg", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplateEntity sendPendingMsg(@RequestBody JSONObject jsonObj) {
        log.info("[x70,收到]调用发送待处理模板信息接口" + String.valueOf(jsonObj));
        this.sendMsgService.sendPendingMsg(jsonObj.getString("Id"), jsonObj.getString("Text"), userUtil.formatLoginName(jsonObj.getString("LoginName")));
        return new ResultTemplateEntity("Yes", "连接成功");
    }

    /**
     * 接口4：发送红包
     */
    @RequestMapping(value = "/sendRedPack", method = RequestMethod.POST)
    @ResponseBody
    public RedPackSendResp sendRedPack(@RequestBody String requestStr) {
        return this.redPackService.sendRedPackByEncryptStr(requestStr);
    }

    /**
     * 接口5：红包订单查询
     */
    @RequestMapping(value = "/queryRedPackInfo", method = RequestMethod.POST)
    @ResponseBody
    public RedPackQueryResp queryRedPackInfo(@RequestBody String requestStr) {
        return redPackService.queryRedPackInfoByEncryptStr(requestStr);
    }
}
