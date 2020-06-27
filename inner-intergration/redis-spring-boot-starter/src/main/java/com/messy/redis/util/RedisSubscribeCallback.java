package com.messy.redis.util;

 
public interface RedisSubscribeCallback {
    void callback(String msg);
}
