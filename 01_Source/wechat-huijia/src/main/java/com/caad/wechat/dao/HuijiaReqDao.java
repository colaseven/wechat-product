package com.caad.wechat.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caad.wechat.entity.HuijiaReq;

@Repository("huijiaReqDao")
public interface HuijiaReqDao extends JpaRepository<HuijiaReq, Long> {

    @Modifying
    @Query(value = ("DELETE FROM HuijiaReq h WHERE h.status=:status AND h.createtime<:createtime"))
    void deleteByStatusAndCreatetime(@Param("status") String status, @Param("createtime") Date createtime);

}
