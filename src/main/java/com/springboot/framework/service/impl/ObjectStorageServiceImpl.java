package com.springboot.framework.service.impl;

import com.springboot.framework.model.ObjectStorageClient;
import com.springboot.framework.config.ObjectStorageConfig;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.model.ObjectStorageObjectMetadata;
import com.springboot.framework.service.ObjectStorageService;
import com.springboot.framework.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/13 23:25
 */
@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ObjectStorageConfig objectStorageConfig;
    @Resource
    private ObjectStorageClient uploadClient;
    @Resource
    private ObjectStorageClient downloadClient;

    @Override
    public String upload(MultipartFile file) {
        String originFileName = file.getOriginalFilename();
        String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
//        String fileType = FileContentTypeUtil.getContentType(suffixName);
        // 设置文件名
        String filePathName = generateRelativeStoragePath(suffixName);
//        byte[] fileContent = null;
//        try {
//            fileContent = file.getBytes();
//        } catch (Exception e) {
//            logger.error("Cannot get file content from {}.", originFileName);
//            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "不能读取" + originFileName + "内容");
//        }
        ObjectStorageObjectMetadata metadata = new ObjectStorageObjectMetadata();
        // 设置上传文件长度
        metadata.setContentLength(file.getSize());

//        // 设置上传MD5校验
//        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
//        metadata.setContentMD5(md5);
//        metadata.setContentType(fileType);

        // 存储
        try {
            uploadClient.putObject(objectStorageConfig.getBucketName(), filePathName, file.getInputStream(), metadata);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ObjectStorage error", e);
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "ObjectStorage exception");
        } finally {
            uploadClient.shutdown();
        }
//        String path = objectStorageConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
        String path = objectStorageConfig.getDownloadEndpoint() + "/" + filePathName;
        if (FileUtil.isImg(suffixName)) {
            // 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
            path += StringUtil.isNotBlank(objectStorageConfig.getStyleName()) ? "?x-image-process=image/" + objectStorageConfig.getStyleName() : "";
        }
        return path;
    }

    @Override
    public String uploadImageBase64(byte[] imageBytes) {
        String fileType = "image/jpeg";
        // 设置文件名
        String filePathName = generateRelativeStoragePath("jpeg");
        ObjectStorageObjectMetadata meta = new ObjectStorageObjectMetadata();
        // 设置上传文件长度
        meta.setContentLength((long) imageBytes.length);
        // 设置上传MD5校验
        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(imageBytes));
        meta.setContentMD5(md5);
        meta.setContentType(fileType);

        // 存储
        try {
            uploadClient.putObject(objectStorageConfig.getBucketName(), filePathName, new ByteArrayInputStream(imageBytes), meta);
        } catch (Exception e) {
            logger.error("ObjectStorage error", e);
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "ObjectStorage exception");
        } finally {
            uploadClient.shutdown();
        }
        String path = objectStorageConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
        // 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
        return path + (StringUtil.isNotBlank(objectStorageConfig.getStyleName()) ? "?x-oss-process=style/" + objectStorageConfig.getStyleName() : "");
    }

    @Override
    public String uploadFile(File file) {
        // 存储
        InputStream is = null;
        try {
            String originFileName = file.getName();
            String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
            String fileType = FileContentTypeUtil.getContentType(suffixName);
            // String fileType = "application/octet-stream";
            // 设置文件名
            String filePathName = generateRelativeStoragePath(suffixName);
            is = new FileInputStream(file);
            byte[] fileContent = new byte[is.available()];
            is.read(fileContent);
            ObjectStorageObjectMetadata meta = new ObjectStorageObjectMetadata();
            // 设置上传文件长度
            meta.setContentLength(file.length());
            // 设置上传MD5校验
            String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
            meta.setContentMD5(md5);
            meta.setContentType(fileType);
            uploadClient.putObject(objectStorageConfig.getBucketName(), filePathName, new ByteArrayInputStream(fileContent), meta);
            String path = objectStorageConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
            return path;
        } catch (Exception e) {
            logger.error("ObjectStorage error", e);
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "ObjectStorage exception");
        } finally {
            uploadClient.shutdown();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public byte[] download(String url) {
        InputStream is = null;
        try {
            String key = url.split(objectStorageConfig.getDownloadEndpoint() + "/")[1];
            is = downloadClient.getObject(objectStorageConfig.getBucketName(), key).getObjectContent();
            byte[] data = IOUtils.readStreamAsByteArray(is);
            return data;
        } catch (Exception e) {
            logger.error("下载文件异常,url={}", url, e);
            e.printStackTrace();
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "下载文件异常");
        } finally {
            downloadClient.shutdown();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 获取存储的相对路径 规则path + / + yyyyMMddHH + uuid
     *
     * @param suffixName 后缀名
     * @return String
     */
    private String generateRelativeStoragePath(String suffixName) {
        SimpleDateFormat yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH");
        String time = yyyyMMddHH.format(new Date());
        String uuid = StringUtil.getUUID(8);
        StringBuilder sb = new StringBuilder();
        String storagePath = this.objectStorageConfig.getStoragePath();
        if (StringUtil.isNotBlank(storagePath)) {
            sb.append(storagePath).append("/");
        }
        sb.append(time).append(uuid);
        if (StringUtil.isNotBlank(suffixName)) {
            sb.append(".").append(suffixName);
        }
        return sb.toString();
    }

}