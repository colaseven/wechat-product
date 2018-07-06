package com.caad.wechat.redis;

import com.caad.wechat.utils.viss.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.LinkedHashSet;
import java.util.Set;


public class JedisClusterFactory implements RedisFactory<JedisCluster> {

    private static Log log = LogFactory.getLog(JedisPoolFactory.class);

    private JedisCluster jedisCluster;

    @Override
    public void init(RedisConfig redisConfig) {
        Set<HostAndPort> hostandports = getHostAndPorts(redisConfig.getHost());
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        this.jedisCluster = new JedisCluster(hostandports, 2000, 3000, 5, genericObjectPoolConfig);

    }

    @Override
    public JedisCluster buildRedis() {
        return jedisCluster;
    }

    private Set<HostAndPort> getHostAndPorts(String host) {
        log.info("解析HostAndPort:" + host);
        Set<HostAndPort> hostandports = new LinkedHashSet<>();
        if (StringUtils.isEmpty(host)) {
            return hostandports;
        }
        String[] ary1 = host.split(",");
        for (String string1 : ary1) {
            //log.info("\t" + string1);
            String[] ary2 = string1.split(":");
            if (ary2.length != 2) {
                log.info("没有发现IP:PORT数据配置");
                continue;
            }
            //log.info("\t" + ary2[0].trim() + "\t" + ary2[1].trim());
            hostandports.add(new HostAndPort(ary2[0].trim(), Integer.valueOf(ary2[1].trim())));
        }
        log.info("解析完成：" + hostandports);
        return hostandports;
    }
}
