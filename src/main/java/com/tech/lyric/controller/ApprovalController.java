/*
 * @Author: chengkun
 * @Date: 2025-05-10 10:37:54
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 11:55:03
 * @FilePath: /lyric/src/main/java/com/tech/lyric/controller/ApprovalController.java
 * @Description: 
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.lyric.enums.ApprovalState;
import com.tech.lyric.service.ApprovalService;

@RestController
@RequestMapping("/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping("/getCurrentState")
    public String getCurrentState() {
        ApprovalState currentState = null;
        try {
            currentState = approvalService.getCurrentState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentState == null) {
            return "No current state";
        }
        return approvalService.getCurrentState().toString();
    }

    @RequestMapping("/submit")
    public String submit() {
        approvalService.submitApproval();
        return "Submitted, current state: " + approvalService.getCurrentState();
    }

    @PostMapping("/approve")
    public String approve() {
        approvalService.approve();
        return "Approved, current state: " + approvalService.getCurrentState();
    }

    @PostMapping("/reject")
    public String reject() {
        approvalService.reject();
        return "Rejected, current state: " + approvalService.getCurrentState();
    }
}
