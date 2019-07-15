package com.springboot.framework.model;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSCredentialsProvider;
import com.qcloud.cos.region.Region;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/16 1:57
 */
public class ObjectStorageClient extends COSClient {
    /**
     * 自定义构造方法
     */
    public ObjectStorageClient(String secretId, String secretKey, String endpoint) {
        this(new BasicCOSCredentials(secretId, secretKey), new ClientConfig(new Region(endpoint)));
    }

    public ObjectStorageClient(COSCredentials cred, ClientConfig clientConfig) {
        super(cred, clientConfig);
    }

    public ObjectStorageClient(COSCredentialsProvider credProvider, ClientConfig clientConfig) {
        super(credProvider, clientConfig);
    }
}
