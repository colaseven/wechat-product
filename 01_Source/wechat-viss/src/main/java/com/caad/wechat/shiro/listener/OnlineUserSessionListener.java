package com.caad.wechat.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class OnlineUserSessionListener implements SessionListener {


    @Override
    public void onStart(Session session) {
        //log.info("session start:" + session.getHost());
    }

    @Override
    public void onStop(Session session) {

        //log.info("session stop");
    }

    @Override
    public void onExpiration(Session session) {
        //log.info("session onExpiration");
    }
}
