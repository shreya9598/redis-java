package com.opredis.handler;

import com.opredis.parser.Resp;
import java.util.*;
import com.opredis.services.RespService;

public class RespHandler{

    public static byte[] hanndleResp(String str){
        System.out.println("Handling RESP command: " + str);
        List<String> parseList = Resp.parse(str);

        switch (parseList.get(0)) {
            case "PING":
                System.out.println("Received PING command");
                break;
            case "SET":
                System.out.println("Received SET command");
                return setHandler(parseList);
            case "GET":
                System.out.println("Received GET command");
                return getHandler(parseList);
            default:
                System.out.println("Unknown command: " + parseList.get(0));
        }
        return null;
    }

    public static byte[] getHandler(List<String> parseList){
        if(parseList.size() < 2){
            return Resp.error("Invalid RESP command");
        }

        String key = parseList.get(1);
        String val = RespService.getResp(key);
        if (val == null) return Resp.error("Key Not Found");
        return Resp.bulkString(val);
    }

    public static byte[] setHandler(List<String> parseList){
        if(parseList.size() < 3){
            return Resp.error("Invalid RESP command");
        }

        String key = parseList.get(1);
        String val = parseList.get(2);

        RespService.setResp(key, val);
        return Resp.OK;
    }
}