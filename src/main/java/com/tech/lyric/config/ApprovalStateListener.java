/*
 * @Author: chengkun
 * @Date: 2025-05-12 22:27:18
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-12 22:33:57
 * @FilePath: /lyric/src/main/java/com/tech/lyric/config/ApprovalStateListener.java
 * @Description: 状态监听器
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */

package com.tech.lyric.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.tech.lyric.enums.ApprovalEvent;
import com.tech.lyric.enums.ApprovalState;

@Component
public class ApprovalStateListener extends StateMachineListenerAdapter<ApprovalState, ApprovalEvent> {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void stateChanged(State<ApprovalState, ApprovalEvent> from, State<ApprovalState, ApprovalEvent> to) {
        if (from == null) {
            logger.info("审批流程初始状态: {}", to.getId());
        } else {
            logger.info("审批流程状态从 {} 变更为 {}", from.getId(), to.getId());
        }
    }

    @Override
    public void transition(Transition<ApprovalState, ApprovalEvent> transition) {
        if (transition.getTrigger() != null && transition.getTrigger().getEvent() != null) {
            logger.info("触发事件: {}", transition.getTrigger().getEvent());
        }
    }

    @Override
    public void stateMachineStarted(StateMachine<ApprovalState, ApprovalEvent> stateMachine) {
        logger.info("状态机启动");
    }

    @Override
    public void stateMachineStopped(StateMachine<ApprovalState, ApprovalEvent> stateMachine) {
        logger.info("状态机停止");
    }

}