/*
 * @Author: chengkun
 * @Date: 2025-05-09 23:08:22
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 12:20:06
 * @FilePath: /lyric/src/main/java/com/tech/lyric/service/ApprovalService.java
 * @Description: 审批服务接口
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.service;

import com.tech.lyric.enums.ApprovalState;

public interface ApprovalService {

    public void submitApproval();

    public void approve();

    public void reject();

    public ApprovalState getCurrentState();
}
