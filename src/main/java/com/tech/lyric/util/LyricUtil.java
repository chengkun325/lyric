/*
 * @Author: chengkun
 * @Date: 2025-05-10 19:41:11
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 19:43:26
 * @FilePath: /lyric/src/main/java/com/tech/lyric/util/LyricUtil.java
 * @Description: LyricUtil
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.util;

import java.util.Objects;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tech.lyric.constant.CommonConstant;

import jakarta.servlet.http.HttpServletRequest;

public class LyricUtil {
    
    /**
     * 获取当前请求对象
     * @return 当前请求
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取当前请求的token
     * @return 当前请求的token
     */
    public static String getToken() {
        String token = LyricUtil.getRequest().getHeader(CommonConstant.USER_TOKEN);
        return "null".equals(token) ? null : token;
    }

    /**
     * 获取当前请求的用户id
     * @return 当前请求的用户id
     */
    public static Integer getUserId(){
        String token = LyricUtil.getToken();
        if (!StringUtils.hasText(token)) {
            return null;
        }
        Integer userId;
        try {
            userId = Integer.parseInt(JWTUtil.parseJWT(token).getSubject());
            return userId;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
