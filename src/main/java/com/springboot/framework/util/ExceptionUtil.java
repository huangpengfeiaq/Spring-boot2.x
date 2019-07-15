package com.springboot.framework.util;


import com.springboot.framework.constant.Errors;
import com.springboot.framework.exception.BusinessException;

/**
 * 异常工具类
 *
 * @author jzsong@uworks.cc
 */
public class ExceptionUtil {
    public static void throwException(int code, String codeLabel) {
        throw new BusinessException(code, codeLabel, codeLabel);
    }

    public static void throwException(Errors error) {
        throw new BusinessException(error, error.label);
    }
}
