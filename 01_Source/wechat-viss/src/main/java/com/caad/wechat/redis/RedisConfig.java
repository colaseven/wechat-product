package com.caad.wechat.redis;

import com.caad.wechat.utils.viss.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.JedisPoolConfig;


public class RedisConfig extends JedisPoolConfig {
    private static Log log = LogFactory.getLog(RedisConfig.class);

    //    public static final int MODE_BASIC = 0;
    public static final int MODE_POOL = 1;
    public static final int MODE_CLUSTOR = 2;
    private int mode;
    private String host;
    private String password;
    private int db;

    public void setMode(String mode) {
        if (StringUtils.isEmpty(mode)) {
            this.mode = getModeByHost();
        }
//        else if (mode.equalsIgnoreCase("basic")) {
//            this.mode = MODE_BASIC;
//        }
        else if (mode.equalsIgnoreCase("pool") || mode.equalsIgnoreCase("default")) {
            this.mode = MODE_POOL;
        } else if (mode.equalsIgnoreCase("cluster")) {
            this.mode = MODE_CLUSTOR;
        } else {
            log.info("RedisConfig配置文件类型未被识别：" + mode);
            this.mode = getModeByHost();
        }
    }

    private int getModeByHost() {
        if (StringUtils.isEmptyOrNull(host))
            return MODE_POOL;
        if (host.indexOf(",") > 0)
            return MODE_CLUSTOR;
        return MODE_POOL;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
