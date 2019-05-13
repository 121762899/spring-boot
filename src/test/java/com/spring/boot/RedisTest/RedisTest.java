package com.spring.boot.RedisTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;
    
    @Test
    public void distributedLockTest() {
        boolean b = redisUtil.distributedLock("20190513", 100l);
        System.out.println(b);
    }
    
    
}
