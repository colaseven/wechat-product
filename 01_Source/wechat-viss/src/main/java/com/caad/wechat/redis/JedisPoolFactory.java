package com.caad.wechat.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisPoolFactory implements RedisFactory<Jedis> {

    private static Log log = LogFactory.getLog(JedisPoolFactory.class);

    private JedisPool pool;
    private RedisConfig config;

    @Override
    public void init(RedisConfig config) {
        this.config = config;
        HostAndPort hostAndPort = getHostAndPorts(config.getHost());
        this.pool = new JedisPool(config, hostAndPort.getHost(), hostAndPort.getPort(), 10000, config.getPassword());
    }

    @Override
    public Jedis buildRedis() {
        Jedis jedis = this.pool.getResource();
        jedis.select(config.getDb());
        return jedis;
    }

    private HostAndPort getHostAndPorts(String host) {
        if (host.indexOf(":") == -1) {
            log.error("redis地址格式错误，未发现\":\"");
            return null;
        }
        String[] arr = host.split(":");
        if (arr.length != 2) {
            log.error("redis地址格式错误，发现多个\":\"");
            return null;
        }
        return new HostAndPort(arr[0].trim(), Integer.valueOf(arr[1].trim()));
    }
}
