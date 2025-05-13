/*
 * @Author: chengkun
 * @Date: 2025-05-10 21:52:27
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 21:54:10
 * @FilePath: /lyric/src/main/java/com/tech/lyric/filter/JWTFilter.java
 * @Description: JWTFilter
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.tech.lyric.enums.CodeMsg;
import com.tech.lyric.util.JWTUtil;
import com.tech.lyric.util.LyricUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter implements Filter{

    /**
     * 白名单
     */
    private static final List<String> WHITE_LIST = Arrays.asList("/user/login");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                String path = httpRequest.getRequestURI();

                // 1、白名单直接放行
                if (WHITE_LIST.contains(path)) {
                    chain.doFilter(request, response);
                    return;
                }

                // 2、从 Header 提取 Token
                String token = LyricUtil.getToken();

                try {
                    // 3、验证 Token
                    // 3.1、如果token为空，则放行
                    if (token == null) {
                        chain.doFilter(request, response);
                        return;
                    }
                    // 3.2、如果token不合法，则返回401
                    if (!JWTUtil.validateToken(token)) {
                        sendError(httpResponse, CodeMsg.PARAMETER_ERROR);
                        return;
                    }

                    // 4、将用户信息设置到请求中，供后续接口使用
                    //todo 从token中获取用户信息
                    Long userId = Long.parseLong(JWTUtil.parseJWT(token).getSubject());
                    httpRequest.setAttribute("currentUser", userId);

                    // 5、放行
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    sendError(httpResponse, CodeMsg.PARAMETER_ERROR);
                }
    }

    /**
     * 发送错误响应
     * @param response 响应对象
     * @param message 错误消息
     * @param code 错误状态码
     * @throws IOException 异常
     */
    private void sendError(HttpServletResponse response, CodeMsg codeMsg) throws IOException {
        int code = CodeMsg.PARAMETER_ERROR.getCode();
        String msg = CodeMsg.PARAMETER_ERROR.getMsg();
        response.setStatus(code);
        response.setContentType("application/json");
        response.getWriter().write("{\"code\": " + code + ", \"message\": \"" + msg + "\"}");
    }
    
}
