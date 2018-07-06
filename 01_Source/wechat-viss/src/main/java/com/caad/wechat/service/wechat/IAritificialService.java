package com.caad.wechat.service.wechat;

import com.caad.wechat.model.viss.aritificial.*;
import com.caad.wechat.model.viss.common.PagedList;
import com.caad.wechat.model.viss.product.EntrustQueryModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IAritificialService {

    /**
     * 获取配置的省市区
     */

    List<NodeModel> getAreas(@RequestParam("propertyId") String propertyId, @RequestParam("level") String level, @RequestParam("id") long id);

    /**
     * 获取所有省市区
     */
    List<NodeModel> getAllAreas(@RequestParam("id") long id);


    /**
     * 获取物业类型选择项
     */
    Map<String, List<NodeModel>> getSelect();


    /**
     * 获取附件类型
     */
    List<DataSourceModel> getAttachmentTypes();


    /**
     * 保存单据
     */
    EntrustResultModel saveBaseInfo(@RequestParam("param") WeChatEntrustInformationParam param);


    /**
     * 快捷回价历史记录
     */
    PagedList<WeChatEntrustinfomationResult> getHistoryList(@RequestParam("query") EntrustQueryModel query);


    /**
     * 获取单据详情
     */
    WeChatEntrustinfomationResult getEntrustInfo(@RequestParam("entrustId") long entrustId);


    /**
     * 获取字典项
     */
    List<NodeModel> getDict(@RequestParam("dictType") String dictType);

}