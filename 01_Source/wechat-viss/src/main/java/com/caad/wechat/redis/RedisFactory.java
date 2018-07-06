package com.caad.wechat.redis;


public interface RedisFactory<T> {

    public void init(RedisConfig redisConfig);

    public T buildRedis();
}
