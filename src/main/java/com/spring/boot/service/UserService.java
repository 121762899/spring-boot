package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.entity.User;
import com.spring.boot.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * page 当前页数<br>
     * size 当前展示的数据<br>
     * 作者: 每特教育-余胜军<br>
     * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
     * 
     * @param page
     * @param size
     * @return
     */
    public PageInfo<User> findUserList(int page, int size) {
        // 开启分页插件,放在查询语句上面
        PageHelper.startPage(page, size);
        List<User> listUser = userMapper.findUserList();
        // 封装分页之后的数据
        PageInfo<User> pageInfoUser = new PageInfo<User>(listUser);
        return pageInfoUser;
    }

}