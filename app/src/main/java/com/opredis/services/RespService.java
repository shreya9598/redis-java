package com.opredis.services;

import com.opredis.RedisValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RespService{

    static Map<String, RedisValue> cache = new ConcurrentHashMap<>();

    public static String getResp(String key){
        // Simulate a response from a Redis server
        return (String) cache.get(key).getValue();
    }

    public static void setResp(String key, String value, int ttl){
        // Simulate setting a value in a Redis server
        System.out.println("Setting key " + key + " to value " + value);
        RedisValue redisValue = new RedisValue();
        redisValue.setValue(value);
        redisValue.setExpirationMillis(ttl);
        cache.put(key, redisValue);
    }


}