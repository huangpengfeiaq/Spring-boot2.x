package com.springboot.framework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author huangpengfei
 * @version 1.0
 * @since 2019/7/13 23:22
 */
public interface ObjectStorageService {
    /**
     * oss上传文件，返回文件访问路径
     *
     * @param file：文件
     * @return String
     */
    String upload(MultipartFile file);

    /**
     * base64code方式上传
     *
     * @param imageBytes 文件流
     * @return String
     */
    String uploadImageBase64(byte[] imageBytes);

    /**
     * File方式上传
     *
     * @param file 文件
     * @return String
     */
    String uploadFile(File file);

    /**
     * 下载文件
     *
     * @param url 路径
     * @return String
     */
    byte[] download(String url);
}
