/*
 * @Author: chengkun
 * @Date: 2025-05-09 22:23:02
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 15:46:35
 * @FilePath: /lyric/src/main/java/com/tech/lyric/LyricApplication.java
 * @Description: 主程序
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tech.lyric.dao")
public class LyricApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyricApplication.class, args);
    }

}
