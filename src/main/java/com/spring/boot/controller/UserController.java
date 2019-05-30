package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.spring.boot.entity.User;
import com.spring.boot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    public PageInfo<User> findUserList(Long partionId,int page, int size) {
        log.info("This is lombok log");
        return userService.findUserList(partionId,page, size);
    }
    
}
