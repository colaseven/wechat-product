package com.caad.wechat.shiro.session;

import com.caad.wechat.redis.RedisUtils;
import com.caad.wechat.utils.viss.SerializeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;


public class RedisSessionDao extends AbstractSessionDAO {


    private static final String PART_NAME = "shiro";

    // 会话key
    private final static String AQS_SHIRO_SESSION_ID = "wechat-shiro-session-id";
    // 全局会话key
    private final static String AQS_SERVER_SESSION_ID = "wechat-server-session-id";
    // 全局会话列表key
    private final static String AQS_SERVER_SESSION_IDS = "wechat-server-session-ids";
    // code key
    private final static String AQS_SERVER_CODE = "wechat-server-code";
    // 局部会话key
    private final static String AQS_CLIENT_SESSION_ID = "wechat-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String AQS_CLIENT_SESSION_IDS = "wechat-client-session-ids";

    @Override
    public void update(Session session) {
        try {
            // 如果会话过期/停止 没必要再更新了
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
            Serializable sessionId = session.getId();
            MySession mySession = (MySession) session;
            MySession cacheSession = (MySession) doReadSession(sessionId);
            if (null != cacheSession) {
                mySession.setStatus(cacheSession.getStatus());
                mySession.setAttribute("FORCE_LOGOUT", cacheSession.getAttribute("FORCE_LOGOUT"));
            }
            byte[] byteKey = (AQS_SHIRO_SESSION_ID + ":" + sessionId).getBytes();
            RedisUtils.sset(PART_NAME, byteKey, SerializeUtils.serialize(session));
            RedisUtils.setTimeOut(PART_NAME, AQS_SHIRO_SESSION_ID + ":" + sessionId, (int) (session.getTimeout() / 1000));
            //log.debug("doUpdate:sessionId={}",sessionId);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }
    }

    @Override
    public void delete(Session session) {
        try {
            String sessionId = session.getId().toString();
            byte[] byteKey = (AQS_SHIRO_SESSION_ID + ":" + sessionId).getBytes();
            RedisUtils.sdel(PART_NAME, byteKey);
            //log.debug("doDelete:sessionId={}",sessionId);
        } catch (Exception e) {
            // log.error(e.getMessage(),e);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        try {
            byte[] byteKey = (AQS_SHIRO_SESSION_ID + ":" + sessionId).getBytes();
            RedisUtils.sset(PART_NAME, byteKey, SerializeUtils.serialize(session));
            RedisUtils.setTimeOut(PART_NAME, AQS_SHIRO_SESSION_ID + ":" + sessionId, (int) (session.getTimeout() / 1000));
            //log.debug("doCreate:sessionId={}",sessionId);
        } catch (Exception e) {
            // log.error(e.getMessage(),e);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        try {
            byte[] byteKey = (AQS_SHIRO_SESSION_ID + ":" + sessionId).getBytes();
            byte[] byteValue = RedisUtils.sget(PART_NAME, byteKey);
            //log.debug("doReadSession:sessionId={}",sessionId);
            return (Session) SerializeUtils.deserialize(byteValue);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }
        return null;
    }


    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
