/*
 * @Author: chengkun
 * @Date: 2025-05-10 10:34:58
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 11:55:52
 * @FilePath: /lyric/src/main/java/com/tech/lyric/service/impl/ApprovalServiceImpl.java
 * @Description: 
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import com.tech.lyric.enums.ApprovalEvent;
import com.tech.lyric.enums.ApprovalState;
import com.tech.lyric.service.ApprovalService;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private StateMachine<ApprovalState, ApprovalEvent> stateMachine;

    @Override
    public void submitApproval() {
        stateMachine.start();
        stateMachine.sendEvent(ApprovalEvent.SUBMIT);
    }

    @Override
    public void approve() {
        stateMachine.sendEvent(ApprovalEvent.PASS);
    }

    @Override
    public void reject() {
        stateMachine.sendEvent(ApprovalEvent.REJECT);
    }

    @Override
    public ApprovalState getCurrentState() {
        return stateMachine.getState().getId();
    }

}
