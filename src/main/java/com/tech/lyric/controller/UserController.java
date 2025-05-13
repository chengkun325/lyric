/*
 * @Author: chengkun
 * @Date: 2025-05-10 15:31:06
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-11 11:19:01
 * @FilePath: /lyric/src/main/java/com/tech/lyric/controller/UserController.java
 * @Description: 用户控制器
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.lyric.dto.LoginBody;
import com.tech.lyric.service.UserService;
import com.tech.lyric.util.LyricResult;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @PostMapping("/login")
    public LyricResult<?> login(LoginBody loginBody) {
        return userService.login(loginBody);
    }

    @GetMapping("/logout")
    public LyricResult<?> logout() {
        return LyricResult.success();
    }
    
    @Autowired
    private UserService userService;
}
