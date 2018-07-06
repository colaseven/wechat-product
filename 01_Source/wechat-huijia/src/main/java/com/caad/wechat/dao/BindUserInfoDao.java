package com.caad.wechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caad.wechat.entity.UserInfo;

@Repository("bindUserInfoDao")
public interface BindUserInfoDao extends JpaRepository<UserInfo, Long> {

    /**
     * 删除用户信息
     */
    @Modifying
    @Query(value = "DELETE FROM UserInfo u WHERE u.loginname=:loginname")
    void deleteUser(@Param("loginname") String loginname);

    @Modifying
    @Query(value = "DELETE FROM UserInfo u WHERE u.openid=:openid")
    void deleteOpenid(@Param("openid") String openid);

}
