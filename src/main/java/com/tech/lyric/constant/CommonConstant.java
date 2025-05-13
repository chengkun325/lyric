/*
 * @Author: chengkun
 * @Date: 2025-05-10 19:23:08
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 19:39:18
 * @FilePath: /lyric/src/main/java/com/tech/lyric/constant/CommonConstant.java
 * @Description: 公共常量
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.constant;

public class CommonConstant {
    /**
     * 密钥
     */
    public static final String CRYPOTJS_KEY = "chengkunchengkun";

    /**
     * JWT密钥明文 todo：这两个密钥统一下
     */
    public static final String JWT_KEY = "chengkunchengkunchengkunchengkun";

    /**
     * JWT过期时间
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 用户token
     */
    public static final String USER_TOKEN = "user_token_";

    /**
     * 请求头
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 评论和IM邮件发送次数
     */
    public static final int COMMENT_IM_MAIL_COUNT = 1;

    /**
     * 验证码邮件发送次数
     */
    public static final int CODE_MAIL_COUNT = 3;

    /**
     * 验证码
     */
    public static final String USER_CODE = "user_code_";

}
