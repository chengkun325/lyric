/*
 * @Author: chengkun
 * @Date: 2025-05-09 22:23:02
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-12 22:38:43
 * @FilePath: /lyric/src/test/java/com/tech/lyric/LyricApplicationTests.java
 * @Description: 测试类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.junit.jupiter.api.Assertions;

import com.tech.lyric.dao.UserMapper;
import com.tech.lyric.enums.ApprovalState;

import cn.hutool.core.lang.Assert;

import com.tech.lyric.enums.ApprovalEvent;

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

    // 注入状态机
    @Autowired
    private StateMachine<ApprovalState, ApprovalEvent> stateMachine;


    @Test
    void testStateMachine() {
        
        // 启动状态机
        stateMachine.start();
        
        // 验证初始状态
        Assert.equals(ApprovalState.UN_SUBMIT, stateMachine.getState().getId());

        // 测试提交事件
        stateMachine.sendEvent(ApprovalEvent.SUBMIT);
        Assert.equals(ApprovalState.LEADER_CHECK, stateMachine.getState().getId());

        // 测试领导审批通过
        stateMachine.sendEvent(ApprovalEvent.PASS);
        Assert.equals(ApprovalState.HR_CHECK, stateMachine.getState().getId());

        // 测试HR审批通过
        stateMachine.sendEvent(ApprovalEvent.PASS);
        Assert.equals(ApprovalState.FINISH, stateMachine.getState().getId());
    }

}
