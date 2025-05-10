/*
 * @Author: chengkun
 * @Date: 2025-05-09 22:23:02
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 15:57:14
 * @FilePath: /lyric/src/test/java/com/tech/lyric/LyricApplicationTests.java
 * @Description: 测试类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.lyric.dao.UserMapper;

@SpringBootTest
class LyricApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        System.out.println((userMapper.selectList(null)));
        System.out.println(("---------------------------------"));
    }

}
