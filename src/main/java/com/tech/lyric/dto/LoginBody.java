/*
 * @Author: chengkun
 * @Date: 2025-05-10 21:58:19
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 21:58:34
 * @FilePath: /lyric/src/main/java/com/tech/lyric/dto/LoginBody.java
 * @Description: 登录请求体
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.dto;

import lombok.Data;

@Data
public class LoginBody {

    /**
     * 账号（手机号/用户名/邮箱）
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 登录类型（code：通过验证码登录，password：通过密码登录）
     */
    private String loginType;
}
