package com.messy.common.util;

 
public interface RedisSubscribeCallback {
    void callback(String msg);
}
