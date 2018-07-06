package com.caad.wechat.service.wechat;


import com.caad.wechat.model.viss.assessAgent.ReportListDto;
import com.caad.wechat.model.viss.assessAgent.WeChatEntrustPaymentDto;
import com.caad.wechat.model.viss.assessAgent.WeChatEntrustSearch;
import com.caad.wechat.model.viss.assessAgent.WeChatReportDetail;
import com.caad.wechat.model.viss.common.PagedList;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAssessAgentService {

    /**
     * 委托列表
     */
    PagedList<ReportListDto> getHistoryList(@RequestParam("query") WeChatEntrustSearch query);


    /**
     *委托报告详情
     */
    WeChatReportDetail getReportDetail (@RequestParam("entrustId")long entrustId);


    /**
     * 委托报告支付回调
     */
    boolean  weChatConfirmPayment(@RequestParam("weChatEntrustPaymentDto")WeChatEntrustPaymentDto weChatEntrustPaymentDto);

}
