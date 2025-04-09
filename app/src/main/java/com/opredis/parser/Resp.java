package com.opredis.parser;

import java.util.*;
import java.io.*;


public class Resp{
    public static final byte[] CRLF = "\r\n".getBytes();
    public static final byte[] OK = "+OK\r\n".getBytes();
    public static final byte[] ERROR = "-ERR\r\n".getBytes();
    public static final byte[] EMPTY_BULK = "$-1\r\n".getBytes();
    public static final byte[] EMPTY_ARRAY = "*-1\r\n".getBytes();


    public static List<String> parse(String str){
        System.out.println("Parsing RESP command: " + str);
        List<String> result = new ArrayList<>();

        str.split("\\r\\n");
        String[] lines = str.split("\r\n");
    
        for(int i=2;i<lines.length;i+=2){
            // System.out.println("Line " + i + ": " + lines[i]);
            result.add(lines[i]);
        }

        return result;
    }   
}