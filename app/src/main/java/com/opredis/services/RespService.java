package com.opredis.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RespService{

    static Map<String, Object> cache = new ConcurrentHashMap<>();

    public static String getResp(String key){
        // Simulate a response from a Redis server
        String response = "+OK\r\n";
        return (String) cache.get(key);
    }

    public static void setResp(String key, String value){
        // Simulate setting a value in a Redis server
        System.out.println("Setting key " + key + " to value " + value);
        cache.put(key, value);
    }
}