package com.opredis.handler;

import com.opredis.parser.Resp;
import java.util.*;
import com.opredis.services.RespService;

public class RespHandler{

    public static void hanndleResp(String str){
        System.out.println("Handling RESP command: " + str);
        List<String> parseList = Resp.parse(str);

        switch (parseList.get(0)) {
            case "PING":
                System.out.println("Received PING command");
                break;
            case "SET":
                System.out.println("Received SET command");
                break;
            case "GET":
                getHandler(parseList);
                System.out.println("Received GET command");
                break;
            default:
                System.out.println("Unknown command: " + parseList.get(0));
        }
    }

    public static String getHandler(List<String> parseList){
        if(parseList.size() < 2){
            return "-ERR unknown command\r\n";
        }

        String key = parseList.get(1);
        return RespService.getResp(key);
    }
}