package com.caad.wechat.dao;


import com.caad.wechat.entity.ProductPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productPaymentInfoDao")
public interface ProductPaymentInfoDao extends JpaRepository<ProductPaymentInfo, String> {

    ProductPaymentInfo findByBoughtNumber(String out_trade_no);
}
