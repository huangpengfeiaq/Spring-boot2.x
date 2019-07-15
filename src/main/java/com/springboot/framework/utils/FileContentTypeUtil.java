package com.springboot.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/13 23:46
 */
public class FileContentTypeUtil {

    private static Logger logger = LoggerFactory.getLogger(FileContentTypeUtil.class);

    private static Map<String, String> map = null;

    public static void init(String propertieFilePath) {
        try {
            FileContentTypeUtil.map = FileUtil.loadFileProperties(propertieFilePath);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public static String getContentType(String key) {
        if (map != null) {
            return map.get(key);
        }
        return null;
    }
}
