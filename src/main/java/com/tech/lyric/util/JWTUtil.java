/*
 * @Author: chengkun
 * @Date: 2025-05-10 19:28:07
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 19:40:20
 * @FilePath: /lyric/src/main/java/com/tech/lyric/util/JWTUtil.java
 * @Description: JWT工具类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.tech.lyric.constant.CommonConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(CommonConstant.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建JWT（无过期时间）
     * 
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null);// 设置过期时间
        return builder.compact();
    }

    /**
     * 创建JWT（有超时时间）
     *
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis);// 设置过期时间
        return builder.compact();
    }

    /**
     * 获取JwtBuilder
     * 
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return JwtBuilder
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis) {
        SecretKey key = Keys.hmacShaKeyFor(CommonConstant.JWT_KEY.getBytes());
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = CommonConstant.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        return Jwts.builder().subject(subject)
                .issuer("chengkun")
                .issuedAt(now)
                .signWith(key, Jwts.SIG.HS256)
                .expiration(expDate);
    }

    public static void main(String[] args) throws Exception {
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NSIsImlzcyI6ImNoZW5na3VuIiwiaWF0IjoxNzQ2ODc2ODg5LCJleHAiOjE3NDY4ODA0ODl9.DY1wY2enMXkqOr0mdhZayLATslX_lPkc_Q5Nlff_KVM";
        Claims claims = parseJWT(token);
        // String jwt = createJWT("12345");
        System.out.println("claims: " + claims);
        // System.out.println("jwt: " + jwt);
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey key = Keys.hmacShaKeyFor(CommonConstant.JWT_KEY.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
    }

    /**
     * 验证 Token
     * @param token Token 字符串
     * @return 是否有效
     */
    public static Boolean validateToken(String token) {
        try {
            parseJWT(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
