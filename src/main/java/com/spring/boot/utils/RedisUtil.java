package com.spring.boot.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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
    
    
    private boolean lockTask(String redisLockKey) {
        boolean isOK = false;
        try (Jedis jedis = jedisPool.getResource()) {
            int retryTime = 3;
            while(--retryTime >= 0 && !isOK) {
                Transaction tx = jedis.multi();
                tx.get(redisLockKey);
                tx.setex(redisLockKey, 10, "1");
                List<Object> redisReturn = tx.exec();
                if(redisReturn.get(0) != null) {
                    isOK = false;
                    Thread.sleep(1000);
                } else {
                    isOK = true;
                    break;
                }
            }
        } catch(Throwable e) {
            isOK = true;
        }
//        if(isOK == false) {
//            throw new BusinessException(1240, "任务被锁定,请退出重试");
//        }
        return isOK;
    }
    
}
