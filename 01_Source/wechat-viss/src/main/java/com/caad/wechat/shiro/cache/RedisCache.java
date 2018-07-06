package com.caad.wechat.shiro.cache;

import com.caad.wechat.utils.viss.SerializeUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;


public class RedisCache<K, V> implements Cache<K, V> {


    //private static final String REDIS_SHIRO_CACHE = "shiroCache";
    private static final int REDIS_TIME_OUT = 3600;

    private String name;

    private RedisManager redisManager;

    public RedisCache(String name, RedisManager redisManager) {
        this.name = name;
        this.redisManager = redisManager;
    }

    @Override
    public V get(K key) throws CacheException {
        byte[] byteKey = buildKey(key).getBytes();
        byte[] byteValue = new byte[0];
        try {
            byteValue = redisManager.getValue(byteKey);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }

        return (V) SerializeUtils.deserialize(byteValue);
    }

    @Override
    public V put(K key, V val) throws CacheException {
        V previos = get(key);
        byte[] byteKey = buildKey(key).getBytes();
        byte[] byteValue = SerializeUtils.serialize(val);
        try {
            redisManager.saveValue(byteKey, byteValue);
            redisManager.setTimeOut(buildKey(key), REDIS_TIME_OUT);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }
        return previos;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        byte[] byteKey = buildKey(key).getBytes();
        try {
            redisManager.delValue(byteKey);
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
        }
        return previos;
    }

    @Override
    public void clear() throws CacheException {
        //TODO
    }

    @Override
    public int size() {
        if (keys() == null)
            return 0;
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        //TODO
//        try {
//            return (Set<K>) redisManager.getKeys(name + ":");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public Collection<V> values() {
        //TODO
        return null;
    }

    private String buildKey(K key) {
        return "wechat_" + name + ":" + String.valueOf(key);
    }
}
