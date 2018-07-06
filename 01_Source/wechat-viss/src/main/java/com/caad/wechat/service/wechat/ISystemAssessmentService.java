package com.caad.wechat.service.wechat;


import com.caad.wechat.model.viss.common.PagedList;
import com.caad.wechat.model.viss.systemAssessment.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ISystemAssessmentService {

    /**
     * 搜索小区、楼栋、单套
     */
    List<InquiryInfoParam> searcharAddress(@RequestParam("model") SearchInquiryModel model);



    /**
     * 系统询价初始化
     */
    MInquiryModel inquiry(@RequestParam("param") GetInquryParam param);

    /**
     * 系统询价详情
     */
    SystemAssessmentResult getSystemAssessmentDetail(@RequestParam("param") GetInquryParam param);

    /**
     * 系统询价
     */
    AssessmentPriceExt makeAssessmentPrice(@RequestParam("model") AssessmentResult model);

    /**
     * 获取楼栋信息
     */
    List<DataItemExt> searchFloors(@RequestParam("param") SearchInquiryParam param);

    /**
     * 获取小区信息、图片
     */
    ProjectInfoResult getProjectInfo(@RequestParam("propertyId") int propertyId, @RequestParam("communityId") String communityId);


    /**
     * 获取系统询价列表
     */
    PagedList<InquiryHistoryResultModel> historyList(@RequestParam("query") InquiryHistoryModel query);


    /**
     * 获取单套列表
     */
    Rooms getResidentRoom(@RequestParam("param") SearchInquiryParam param);

}
