/*
 * @Author: chengkun
 * @Date: 2025-05-10 22:02:16
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 22:04:34
 * @FilePath: /lyric/src/main/java/com/tech/lyric/vo/UserVo.java
 * @Description: 用户视图类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户类型[0:admin，1:管理员，2:普通用户]
     */
    private Integer userType;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 性别[1:男，2:女，0:保密]
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户等级
     */
    private Integer userLv;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 订阅
     */
    private String subscribe;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 最终修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 最终修改人
     */
    private String updateBy;

    /**
     * Token
     */
    private String accessToken;
}
