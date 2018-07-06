package com.caad.wechat.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;


public class MySessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        MySession mySession = new MySession();
        if (null != sessionContext && sessionContext instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request) {
                mySession.setHost(request.getRemoteAddr());
                mySession.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return mySession;
    }
}
