/*
 * @Author: chengkun
 * @Date: 2025-05-10 15:32:09
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-11 10:54:06
 * @FilePath: /lyric/src/main/java/com/tech/lyric/service/UserService.java
 * @Description: 用户服务接口
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.service;

import com.tech.lyric.dto.LoginBody;
import com.tech.lyric.util.LyricResult;

public interface UserService {
    
    LyricResult<?> login(LoginBody loginBody);

    LyricResult<?> logout();
}
