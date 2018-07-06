package com.caad.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caad.wechat.entity.UserBindOp;

@Repository("UserBindOpDao")
public interface UserBindOpDao extends JpaRepository<UserBindOp, Long> {

}
