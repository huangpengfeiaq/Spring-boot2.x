package com.springboot.framework.util;

import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * 响应返回数据工具类
 *
 * @author jzsong@uworks.cc
 */
public class ResponseBOUtil {

    public static <T> ResponseBO<T> success(T data) {
        ResponseBO<T> entity = new ResponseBO<T>();
        entity.setData(data);
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        return entity;
    }

    public static <T> ResponseBO<T> success() {
        ResponseBO<T> entity = new ResponseBO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        return entity;
    }

    public static ResponseBO<Void> fail(Integer httpStatus, Integer code, String message, HttpServletRequest request) {
        ResponseBO<Void> entity = build();
        entity.setCode(code);
        entity.setHttpStatus(httpStatus);
        entity.setException(message);
        if (null != request) {
            entity.setPath(request.getRequestURI());
        }
        return entity;
    }

    public static <T> ResponseBO<T> fail(Integer code, String message) {
        ResponseBO<T> entity = new ResponseBO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseBO<T> fail(String message) {
        ResponseBO<T> entity = new ResponseBO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(Errors.SYSTEM_CUSTOM_ERROR.code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseBO<T> fail(Errors errors) {
        ResponseBO<T> entity = new ResponseBO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(errors.code);
        entity.setException(errors.label);
        return entity;
    }

    private static ResponseBO<Void> build() {
        ResponseBO<Void> entity = new ResponseBO<Void>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        return entity;
    }

    public static ResponseBO<Void> fail(Integer httpStatus, Integer code, String message, HttpServletRequest request, Exception e) {
        ResponseBO<Void> entity = build();
        entity.setCode(code);
        entity.setHttpStatus(httpStatus);
        entity.setException(message);
        if (null != request) {
            entity.setPath(request.getRequestURI());
        }
        entity.setErrMsg(e.getMessage());
        return entity;
    }

    public static <T> ResponseBO<T> addMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_INSERT_FAIL) :
                ResponseBOUtil.success();
    }

    public static <T> ResponseBO<T> message(T data) {
        return data == null ?
                ResponseBOUtil.fail(Errors.SYSTEM_DATA_NOT_FOUND) :
                ResponseBOUtil.success(data);
    }

    public static <T> ResponseBO<T> updMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_UPDATE_ERROR) :
                ResponseBOUtil.success();
    }

    public static <T> ResponseBO<T> delMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_DELETE_FAIL) :
                ResponseBOUtil.success();
    }
}
