/*
 * @Author: chengkun
 * @Date: 2025-05-10 19:55:07
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 19:55:13
 * @FilePath: /lyric/src/main/java/com/tech/lyric/enums/CodeMsg.java
 * @Description: 响应码
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.enums;

/**
 * 响应码
 * @author chengkun
 * @date 2025年03月05日23:28:16
 */
public enum CodeMsg {
    
    SUCCESS(200, "成功！"),

    PARAMETER_ERROR(400, "参数异常！"),

    NOT_LOGIN(300, "未登陆，请登陆后再进行操作！"),

    LOGIN_EXPIRED(300, "登录已过期，请重新登陆！"),

    SYSTEM_REPAIR(301, "系统维护中，敬请期待！"),

    FAIL(500, "服务异常！");

    private int code;

    private String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}