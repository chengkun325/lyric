/*
 * @Author: chengkun
 * @Date: 2025-05-09 22:58:52
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 12:18:30
 * @FilePath: /lyric/src/main/java/com/tech/lyric/config/ApprovalStateMachineConfig.java
 * @Description: 审批流程状态机配置类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.config;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.tech.lyric.enums.ApprovalEvent;
import com.tech.lyric.enums.ApprovalState;

@Configuration
@EnableStateMachine(name = "approvalStateMachine")
public class ApprovalStateMachineConfig extends StateMachineConfigurerAdapter<ApprovalState, ApprovalEvent> {
    
    @Autowired
    private ApprovalStateListener approvalStateListener;
    
    @Override
    public void configure(StateMachineConfigurationConfigurer<ApprovalState, ApprovalEvent> config) throws Exception {
        config.withConfiguration()
                .listener(approvalStateListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<ApprovalState, ApprovalEvent> states) throws Exception {
        states.withStates()
                .initial(ApprovalState.UN_SUBMIT)
                .states(EnumSet.allOf(ApprovalState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ApprovalState, ApprovalEvent> transitions) throws Exception {
        transitions.withExternal().source(ApprovalState.UN_SUBMIT).target(ApprovalState.LEADER_CHECK)
                .event(ApprovalEvent.SUBMIT)
                .and().withExternal().source(ApprovalState.LEADER_CHECK).target(ApprovalState.HR_CHECK)
                .event(ApprovalEvent.PASS)
                .and().withExternal().source(ApprovalState.LEADER_CHECK).target(ApprovalState.UN_SUBMIT)
                .event(ApprovalEvent.REJECT)
                .and().withExternal().source(ApprovalState.HR_CHECK).target(ApprovalState.FINISH)
                .event(ApprovalEvent.PASS)
                .and().withExternal().source(ApprovalState.HR_CHECK).target(ApprovalState.UN_SUBMIT)
                .event(ApprovalEvent.REJECT);
    }

}
