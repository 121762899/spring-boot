package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.entity.User;
import com.spring.boot.mapper.UserMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JedisPool jedisPool;

    /**
     * @param page
     * @param size
     * @return
     */
    public PageInfo<User> findUserList(Long partionId,int page, int size) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set("key2019", "test1", "NX", "EX", 60*5);
        System.out.println("----------"+result);
        // 开启分页插件,放在查询语句上面
        PageHelper.startPage(page, size);
        List<User> listUser = userMapper.findUserList();
        // 封装分页之后的数据
        PageInfo<User> pageInfoUser = new PageInfo<User>(listUser);
        return pageInfoUser;
    }

}