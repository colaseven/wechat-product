package com.caad.wechat.shiro.cache;

import com.caad.wechat.redis.RedisUtils;

import java.util.Set;


public class RedisManager {

    private static final String PART_NAME = "shiro";

    public String getValue(String key, String field) throws Exception {
        return RedisUtils.hget(PART_NAME, key, field);
    }

    public void saveValue(String key, String field, String val) throws Exception {
        RedisUtils.hset(PART_NAME, key, field, val);
    }

    public void delValue(String key, String field) throws Exception {
        RedisUtils.hdel(PART_NAME, key, field);
    }

    public Set<String> getKeys(String key) throws Exception {
        return RedisUtils.hkeys(PART_NAME, key);
    }

    public Set<byte[]> getKeys(byte[] key) throws Exception {
        return RedisUtils.hkeys(PART_NAME, key);
    }

    public byte[] getValue(byte[] key, byte[] field) throws Exception {
        return RedisUtils.hget(PART_NAME, key, field);
        //return RedisUtils.hget(PART_NAME,key,field);
    }

    public void saveValue(byte[] key, byte[] field, byte[] val) throws Exception {
        RedisUtils.hset(PART_NAME, key, field, val);
    }

    public void delValue(byte[] key, byte[] field) throws Exception {
        RedisUtils.hdel(PART_NAME, key, field);
    }

    public byte[] getValue(byte[] key) throws Exception {
        return RedisUtils.sget(PART_NAME, key);

    }

    public void saveValue(byte[] key, byte[] val) throws Exception {
        RedisUtils.sset(PART_NAME, key, val);
    }

    public void delValue(byte[] key) throws Exception {
        RedisUtils.sdel(PART_NAME, key);
    }

    public void setTimeOut(String key, int time) throws Exception {
        RedisUtils.setTimeOut(PART_NAME, key, time);
    }
}
