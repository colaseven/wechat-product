package com.caad.wechat.redis;

import com.caad.wechat.utils.viss.ConfigUtils;
import com.caad.wechat.utils.viss.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RedisUtils {
    private static Log log = LogFactory.getLog(RedisUtils.class);

    private static final String MODE = "mode";
    private static final String HOST = "host";
    private static final String PASSWORD = "password";
    private static final String MAXIDLE = "maxIdle";
    private static final String MAXWAIT = "maxWait";
    private static final String TESTONBORROW = "testOnBorrow";
    private static final String DB = "db";
    private static final String KEY_SEPERATER = ".";
    private static Map<String, RedisFactory> mapJedisFactory = new HashMap<>();

    private RedisUtils() {

    }

    static {
        load();
    }

    private static void load() {
        ConfigUtils.load(RedisUtils.class, "/redis.properties");
    }

    private static Object getRedis(String part) throws Exception {
        RedisFactory redisFactory = null;
        if (mapJedisFactory.containsKey(part)) {
            redisFactory = mapJedisFactory.get(part);
        } else {
            redisFactory = getFactory(part);
            mapJedisFactory.put(part, redisFactory);
        }
        return redisFactory.buildRedis();
    }


    private static RedisFactory getFactory(String part) throws Exception {
        RedisConfig conf = getConfig(part);

        switch (conf.getMode()) {
//            case 0: // RedisConfig.MODE_BASIC
//            {
//                log.info("newJedisFactory() 开始 RedisConfig.MODE_BASIC");
//               // JedisFactory<Jedis> jedisFactory = new JedisBasicFactory();
//                //jedisFactory.init(config);
//                log.info("newJedisFactory() 结束 RedisConfig.MODE_BASIC");
//                //return jedisFactory;
//            }

            case 2: // RedisConfig.MODE_CLUSTER:
                log.info("Redis集群工厂建立开始");
                RedisFactory<JedisCluster> factory = new JedisClusterFactory();
                factory.init(conf);
                log.info("Redis集群工厂建立开始");
                return factory;
            default:
                log.info("Redis连接池工厂建立开始");
                RedisFactory<Jedis> poolFactory = new JedisPoolFactory();
                poolFactory.init(conf);
                log.info("Redis连接池工厂建立结束");
                return poolFactory;

        }
    }

    private static RedisConfig getConfig(String part) throws Exception {
        log.info("Redis配置载入开始：" + part);
        Class clazz = RedisUtils.class;
        String prefix = part + KEY_SEPERATER;
        String mode = prefix + MODE;
        String host = prefix + HOST;
        String password = prefix + PASSWORD;
        String maxIdle = prefix + MAXIDLE;
        String maxWait = prefix + MAXWAIT;
        String testOnBorrow = prefix + TESTONBORROW;
        String db = prefix + DB;

        if ((StringUtils.isEmpty(host) || StringUtils.isEmpty(password) || StringUtils.isEmpty(db))) {
            log.info("Redis配置载入失败：未找到对应配置参数！" + part);
            throw new Exception("Redis配置载入失败：未找到对应配置参数！" + part);
        }

        RedisConfig conf = new RedisConfig();
        conf.setMode(ConfigUtils.getValue(clazz, mode));
        conf.setHost(ConfigUtils.getValue(clazz, host));
        conf.setDb(ConfigUtils.getIntValue(clazz, db, 0));
        conf.setPassword(ConfigUtils.getValue(clazz, password));
        conf.setMaxIdle(ConfigUtils.getIntValue(clazz, maxIdle, 300));
        conf.setTestOnBorrow(ConfigUtils.getBooleanValue(clazz, testOnBorrow, true));
        conf.setMaxWaitMillis(ConfigUtils.getIntValue(clazz, maxWait, 1000));
        log.info("Redis配置载入结束：" + part);
        return conf;
    }

    public static void sset(String part, String key, String val) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.set(key, val);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.set(key, val);
            jedis.close();
        }
    }

    public static String sget(String part, String key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.get(key);
        } else {
            Jedis jedis = (Jedis) object;
            String val = jedis.get(key);
            jedis.close();
            return val;
        }
    }

    public static void sdel(String part, String... keys) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.del(keys);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.del(keys);
            jedis.close();
        }
    }

    public static String hget(String part, String key, String field) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.hget(key, field);
        } else {
            Jedis jedis = (Jedis) object;
            String val = jedis.hget(key, field);
            jedis.close();
            return val;
        }
    }

    public static void hset(String part, String key, String field, String val) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.hset(key, field, val);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.hset(key, field, val);
            jedis.close();
        }
    }

    public static void hdel(String part, String key, String... fields) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.hdel(key, fields);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.hdel(key, fields);
            jedis.close();
        }
    }

    public static List<String> hvals(String part, String key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.hvals(key);
        } else {
            Jedis jedis = (Jedis) object;
            List<String> vals = jedis.hvals(key);
            jedis.close();
            return vals;
        }
    }

    public static Set<String> hkeys(String part, String key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.hkeys(key);
        } else {
            Jedis jedis = (Jedis) object;
            Set<String> set = jedis.hkeys(key);
            jedis.close();
            return set;
        }
    }

//------------------------------字节版----------------------

    public static void sset(String part, byte[] key, byte[] val) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.set(key, val);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.set(key, val);
            jedis.close();
        }
    }

    public static byte[] sget(String part, byte[] key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.get(key);
        } else {
            Jedis jedis = (Jedis) object;
            byte[] val = jedis.get(key);
            jedis.close();
            return val;
        }
    }

    public static void sdel(String part, byte[]... keys) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.del(keys);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.del(keys);
            jedis.close();
        }
    }

    public static byte[] hget(String part, byte[] key, byte[] field) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.hget(key, field);
        } else {
            Jedis jedis = (Jedis) object;
            byte[] val = jedis.hget(key, field);
            jedis.close();
            return val;
        }
    }

    public static void hset(String part, byte[] key, byte[] field, byte[] val) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.hset(key, field, val);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.hset(key, field, val);
            jedis.close();
        }
    }

    public static void hdel(String part, byte[] key, byte[]... fields) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.hdel(key, fields);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.hdel(key, fields);
            jedis.close();
        }
    }

    public static List<byte[]> hvals(String part, byte[] key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return (List<byte[]>) jedisCluster.hvals(key);
        } else {
            Jedis jedis = (Jedis) object;
            List<byte[]> vals = jedis.hvals(key);
            jedis.close();
            return vals;
        }
    }

    public static Set<byte[]> hkeys(String part, byte[] key) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            return jedisCluster.hkeys(key);
        } else {
            Jedis jedis = (Jedis) object;
            Set<byte[]> set = jedis.hkeys(key);
            jedis.close();
            return set;
        }
    }

    public static void setTimeOut(String part, String key, int time) throws Exception {
        Object object = getRedis(part);
        if (object instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) object;
            jedisCluster.expire(key, time);
        } else {
            Jedis jedis = (Jedis) object;
            jedis.expire(key, time);
            jedis.close();
        }
    }
}
