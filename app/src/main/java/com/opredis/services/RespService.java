package com.opredis.services;

public class RespService{

    public static String getResp(String key){
        // Simulate a response from a Redis server
        String response = "+OK\r\n";
        return response;
    }

    public static void setResp(String key, String value){
        // Simulate setting a value in a Redis server
        System.out.println("Setting key " + key + " to value " + value);
    }
}