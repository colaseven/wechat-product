package com.caad.wechat.dao;

import com.caad.wechat.entity.WechatAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("wechatAccessTokenDao")
public interface WechatAccessTokenDao extends JpaRepository<WechatAccessToken, String> {

    WechatAccessToken findByAppId(String appId);
}
