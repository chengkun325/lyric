/*
 * @Author: chengkun
 * @Date: 2025-05-10 15:32:40
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-13 22:40:46
 * @FilePath: /lyric/src/main/java/com/tech/lyric/service/impl/UserServiceImpl.java
 * @Description: 用户服务实现类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.service.impl;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tech.lyric.constant.CommonConstant;
import com.tech.lyric.dao.UserMapper;
import com.tech.lyric.dto.LoginBody;
import com.tech.lyric.entity.User;
import com.tech.lyric.service.UserService;
import com.tech.lyric.util.JWTUtil;
import com.tech.lyric.util.LyricResult;
import com.tech.lyric.util.LyricUtil;
import com.tech.lyric.util.MailUtil;
import com.tech.lyric.util.RedisCache;
import com.tech.lyric.vo.UserVo;

import cn.hutool.crypto.SecureUtil;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public LyricResult<?> login(LoginBody loginBody) {
        String account = loginBody.getAccount();

        // 判断account是手机号还是邮箱
        String phoneRegex = "^1[3-9]\\d{9}$";
        String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isPhone = account.matches(phoneRegex);
        boolean isEmail = account.matches(emailRegex);

        // 如果是验证码登录
        if (loginBody.getLoginType().equals("code")) {
            // 用户输入的验证码
            String code = loginBody.getCode();

            // 判断用户是否存在
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhoneNumber, account)
                    .or().eq(User::getEmail, account);

            User one = userMapper.selectOne(queryWrapper);

            // 如果用户不存在，视为首次登录，注册该用户。
            if (one == null) {
                User user = new User();
                user.setUsername("user_" + account);
                if (isPhone) {
                    user.setPhoneNumber(account);
                }
                if (isEmail) {
                    user.setEmail(account);
                }
                // 插入用户
                userMapper.insert(user);
                
                // 查询用户
                one = userMapper.selectOne(queryWrapper);
            }

            // 如果用户存在且用户输入的验证码等于服务器发送的验证码
            if (one != null && String.valueOf((Integer)redisCache.getCacheObject(CommonConstant.USER_CODE + one.getId() + "_")).equals(code)) {
                
                // 生成token
                String token = JWTUtil.createJWT(one.getId().toString(), CommonConstant.JWT_TTL);

                // 将token存入redis
                redisCache.setCacheObject(CommonConstant.USER_TOKEN + one.getId(), token);

                // 整理用户信息
                UserVo userVO = new UserVo();
                BeanUtils.copyProperties(one, userVO);
                userVO.setPassword(null);
                userVO.setAccessToken(token);
                userVO.setAvatar(domain + one.getAvatar());
                // 返回用户信息
                return LyricResult.success(userVO);
            } else {
                return LyricResult.fail("验证码错误");
            }
        } else {

            // 账号密码登录
            String password = loginBody.getPassword();

            // 密码解密
            password = new String(
                    SecureUtil.aes(CommonConstant.CRYPOTJS_KEY.getBytes(StandardCharsets.UTF_8)).decrypt(password));

            // 构造查询条件
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, account)
                    .or().eq(User::getEmail, account)
                    .or().eq(User::getPhoneNumber, account)
                    .eq(User::getPassword, DigestUtils.md5DigestAsHex(password.getBytes()));

            // 查询用户
            User one = userMapper.selectOne(queryWrapper);

            // 判断用户是否存在
            if (one == null) {
                return LyricResult.fail("账号/密码错误，请重新输入！");
            }
            if (one.getUserStatus() != 1) {
                return LyricResult.fail("账号被冻结！");
            }

            // 生成token
            String token = JWTUtil.createJWT(one.getId().toString(), CommonConstant.JWT_TTL);

            // 将token存入redis
            redisCache.setCacheObject(CommonConstant.USER_TOKEN + one.getId(), token);

            // 整理用户信息
            UserVo userVO = new UserVo();
            BeanUtils.copyProperties(one, userVO);
            userVO.setPassword(null);
            userVO.setAccessToken(token);
            userVO.setAvatar(domain + one.getAvatar());

            // 返回用户信息
            return LyricResult.success(userVO);
        }
    }

    @Override
    public LyricResult<?> logout() {
        String token = LyricUtil.getToken();
        assert token != null;
        Integer userId;
        try {
            userId = LyricUtil.getUserId();
            redisCache.deleteObject(CommonConstant.USER_TOKEN + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LyricResult.success();
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void testSchedule() {
        System.out.println("定时任务执行了");
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MailUtil mailUtil;

    @Value("${user.code.format}")
    private String codeFormat;

    @Value("${qiniu.domain}")
    private String domain;
    
}
