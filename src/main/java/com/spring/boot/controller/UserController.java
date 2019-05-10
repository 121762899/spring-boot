package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.spring.boot.entity.User;
import com.spring.boot.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    public PageInfo<User> findUserList(int page, int size) {
        return userService.findUserList(page, size);
    }
    
}
