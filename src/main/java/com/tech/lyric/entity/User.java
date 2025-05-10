/*
 * @Author: chengkun
 * @Date: 2025-05-10 15:26:30
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 15:31:27
 * @FilePath: /lyric/src/main/java/com/tech/lyric/entity/User.java
 * @Description: 用户实体类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Byte userStatus;    // 0:否 1:是
    private Byte gender;        // 1:男 2:女
    private String avatar;
    private String introduction;
    private Byte userType;      // 用户类型 0:admin 1:管理员 2:普通用户
    private Byte userLv;        // 用户等级
    private Date createTime;
    private Date updateTime;
    private String updateBy;
    private Byte deleted;       // 0:未删除 1:已删除
}
