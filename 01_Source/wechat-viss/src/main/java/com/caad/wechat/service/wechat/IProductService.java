package com.caad.wechat.service.wechat;

import com.caad.wechat.model.viss.product.ProductBoughtHistoryModel;
import com.caad.wechat.model.viss.product.ProductBoughtModel;
import com.caad.wechat.model.viss.product.SysProductResult;
import com.caad.wechat.model.viss.product.WeChatProductTypeModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProductService {

    /**
     * 购买列表
     */
    List<WeChatProductTypeModel> getProductBoughtList(@RequestParam("userOpenId") String userOpenId, @RequestParam("openId") String openId, @RequestParam("propertyId") long propertyId);

    /**
     * 购买产品次数回调
     */
    boolean productBought(@RequestParam("productBoughtModel") ProductBoughtModel productBoughtModel);

    /**
     * 购买记录
     */
    ProductBoughtHistoryModel getBoughtHistory(@RequestParam("userOpenId") String userOpenId);

    /**
     * 可使用的产品列表
     */
    List<SysProductResult> getProductList(@RequestParam("propertyId") long propertyId, @RequestParam("appId") String appId, @RequestParam("openId") String openId, @RequestParam("templateTypeId") String templateTypeId);

}
