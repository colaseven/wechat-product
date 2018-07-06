package com.caad.wechat.service.wechat;

import com.caad.wechat.model.viss.common.CompanyDetail;
import com.caad.wechat.model.viss.common.LoginResult;
import com.caad.wechat.model.viss.common.QueryAreaModel;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.SystemResultTabShow;
import com.caad.wechat.model.viss.common.WeChatUserBindParam;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICommonService {

    /**
     * 获取机构信息
     */
    OrganizationWeChatSettingResult getOrganizationInfo(@RequestParam("openId") String openId);


    /**
     * 增加询价次数
     */

    boolean addProductFreeUse(@RequestParam("openId") String openId, @RequestParam("userOpenId") String userOpenId);


    /**
     * 系统询价次数
     */
    long getSystemAssessmentUserResidueCount(@RequestParam("openId") String openId, @RequestParam("userOpenId") String userOpenId, @RequestParam("propertyId") long propertyId);


    /**
     * 获取查询区域
     */
    QueryAreaModel getPropertyAndArea();


    /**
     * 获取用户使用次数
     */
    long getUserResidueCount(@RequestParam("openId") String openId, @RequestParam("userOpenId") String userOpenId, @RequestParam("productId") long productId);


    /**
     * 获取公司简介
     */
    CompanyDetail getCompanyProfile(@RequestParam("appId") String appId);


    /**
     * 系统询价结果是否显示tab
     */
    SystemResultTabShow getResult(@RequestParam("appId") String appId);

    /**
     * 用户登录
     */
    LoginResult userBinding(@RequestParam("loginName") String loginName, @RequestParam("password") String password, @RequestParam("userOpenId") String userOpenId);


    /**
     * 手机绑定
     */
    String bind(@RequestParam("weChatUserBindParam")WeChatUserBindParam weChatUserBindParam);

}
