package com.spring.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    
    @Autowired
    private JedisPool jedisPool;
    
    public boolean distributedLock(String key,Long time) {
        try(Jedis jedis = jedisPool.getResource()){
            String result = jedis.set(key, "test1", "NX", "EX", time);
            System.out.println("result:"+result);
            if("OK".equals(result)) {
                return true;
            }else {
                return false;
            }
        }
    }
    
}
