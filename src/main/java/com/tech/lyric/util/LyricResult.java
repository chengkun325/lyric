/*
 * @Author: chengkun
 * @Date: 2025-05-10 19:53:43
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 19:54:33
 * @FilePath: /lyric/src/main/java/com/tech/lyric/util/LyricResult.java
 * @Description: 
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.util;

import java.io.Serializable;

import com.tech.lyric.enums.CodeMsg;

import lombok.Data;

@Data
public class LyricResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    private long currentTimeMillis = System.currentTimeMillis();

    public LyricResult() {
        this.code = 200;
    }

    public LyricResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public LyricResult(T data) {
        this.code = 200;
        this.data = data;
    }

    public LyricResult(String message) {
        this.code = 500;
        this.message = message;
    }

    public static <T> LyricResult<T> fail(String message) {
        return new LyricResult<>(message);
    }

    public static <T> LyricResult<T> fail(CodeMsg codeMsg) {
        return new LyricResult<>(codeMsg.getCode(), codeMsg.getMsg());
    }

    public static <T> LyricResult<T> fail(Integer code, String message) {
        return new LyricResult<>(code, message);
    }

    public static <T> LyricResult<T> success(T data) {
        return new LyricResult<>(data);
    }

    public static <T> LyricResult<T> success() {
        return new LyricResult<>();
    }

}
