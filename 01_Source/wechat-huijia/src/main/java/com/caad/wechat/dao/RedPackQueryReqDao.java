package com.caad.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.caad.wechat.entity.RedPackQueryReq;

@Repository("redPackQueryReqDao")
public interface RedPackQueryReqDao extends JpaRepository<RedPackQueryReq, Long> {

}
