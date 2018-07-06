package com.caad.wechat.dao;


import com.caad.wechat.entity.ReportPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("reportPaymentInfoDao")
public interface ReportPaymentInfoDao extends JpaRepository<ReportPaymentInfo, String> {

    ReportPaymentInfo findByBoughtNumber(String out_trade_no);
}
