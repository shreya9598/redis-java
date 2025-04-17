package com.opredis.parser;

import java.util.*;
import java.io.*;


public class Resp{
    public static final byte[] CRLF = "\r\n".getBytes();

    public static final byte[] OK = "+OK\r\n".getBytes();
    public static final byte[] NULL_BULK_STRING = "$-1\r\n".getBytes();
    public static final byte[] NULL_ARRAY = "*-1\r\n".getBytes();


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

    public static byte[] simpleString(String message) {
        return ("+" + message + "\r\n").getBytes();
    }

    public static byte[] error(String message) {
        return ("-ERR " + message + "\r\n").getBytes();
    }

    public static byte[] integer(long value) {
        return (":" + value + "\r\n").getBytes();
    }

    public static byte[] bulkString(String value) {
        if (value == null) return NULL_BULK_STRING;
        return ("$" + value.length() + "\r\n" + value + "\r\n").getBytes();
    }

    public static byte[] array(List<String> values) {
        if (values == null) return NULL_ARRAY;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write(("*" + values.size() + "\r\n").getBytes());
            for (String val : values) {
                out.write(bulkString(val));
            }
        } catch (IOException e) {
            // This should never happen with ByteArrayOutputStream
        }

        return out.toByteArray();
    }
}