/*
 * @Author: chengkun
 * @Date: 2025-05-09 22:56:10
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-10 11:55:40
 * @FilePath: /lyric/src/main/java/com/tech/lyric/entity/Approval.java
 * @Description: 
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.entity;

import com.tech.lyric.enums.ApprovalState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Approval {

    private Integer id;

    private ApprovalState approvalState;
}
