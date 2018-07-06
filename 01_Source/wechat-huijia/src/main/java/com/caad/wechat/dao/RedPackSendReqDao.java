package com.caad.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.caad.wechat.entity.RedPackSendReq;

@Repository("redPackSendReqDao")
public interface RedPackSendReqDao extends JpaRepository<RedPackSendReq, Long> {

}
