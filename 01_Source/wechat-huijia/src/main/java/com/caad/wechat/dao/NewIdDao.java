package com.caad.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caad.wechat.entity.NewId;

@Repository("newIdDao")
public interface NewIdDao extends JpaRepository<NewId, Long> {

}
