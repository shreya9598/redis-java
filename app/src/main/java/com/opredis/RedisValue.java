package com.opredis;

public class RedisValue {

    String value;
    long expirationMillis;



    public String getValue() {
        if (this.isExpired()) return null;
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getExpirationMillis() {
        return this.expirationMillis;
    }

    public void setExpirationMillis(long ttlSeconds) {
        if (ttlSeconds < 0) {
            this.expirationMillis = -1;
            return;
        }
        this.expirationMillis = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    public boolean isExpired() {
        if (this.expirationMillis < 0) return false;
        return System.currentTimeMillis() > this.expirationMillis;
    }
}
