/*
 * @Author: chengkun
 * @Date: 2025-05-11 10:48:02
 * @LastEditors: chengkun
 * @LastEditTime: 2025-05-11 10:52:08
 * @FilePath: /lyric/src/main/java/com/tech/lyric/util/QiniuUtil.java
 * @Description: Qiniu工具类
 * 
 * Copyright (c) 2025 by chengkun, All Rights Reserved. 
 */
package com.tech.lyric.util;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QiniuUtil {

    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @param fileName 文件名
     * @return 文件访问链接
     */
    public String uploadFile(InputStream inputStream, String fileName) {
        try {
            // 创建配置对象
            Configuration cfg = new Configuration(Region.autoRegion());
            UploadManager uploadManager = new UploadManager(cfg);
            
            // 生成上传凭证
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            
            // 上传文件
            Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
            if (response.isOK()) {
                return domain + "/" + fileName;
            }
        } catch (QiniuException e) {
            log.error("七牛云上传文件失败：{}", e.getMessage());
        }
        return null;
    }
    
    /**
     * 删除文件
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public boolean deleteFile(String fileName) {
        try {
            // 创建配置对象
            Configuration cfg = new Configuration(Region.autoRegion());
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            
            // 删除文件
            Response response = bucketManager.delete(bucket, fileName);
            return response.isOK();
        } catch (QiniuException e) {
            log.error("七牛云删除文件失败：{}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 获取文件访问链接
     * @param fileName 文件名
     * @return 文件访问链接
     */
    public String getFileUrl(String fileName) {
        return domain + fileName;
    }
    
    @Value("${qiniu.accessKey}")
    private String accessKey;
    
    @Value("${qiniu.secretKey}")
    private String secretKey;
    
    @Value("${qiniu.bucket}")
    private String bucket;
    
    @Value("${qiniu.domain}")
    private String domain;
}
