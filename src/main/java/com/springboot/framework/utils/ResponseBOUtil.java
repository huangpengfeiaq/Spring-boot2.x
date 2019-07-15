package com.springboot.framework.utils;

import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.constant.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * 响应返回数据工具类
 *
 * @author jzsong@uworks.cc
 */
public class ResponseBOUtil {

    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> entity = new ResponseVO<T>();
        entity.setData(data);
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        return entity;
    }

    public static <T> ResponseVO<T> success() {
        ResponseVO<T> entity = new ResponseVO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(Errors.SUCCESS.code);
        entity.setHttpStatus(HttpStatusCode.OK.value());
        return entity;
    }

    public static ResponseVO<Void> fail(Integer httpStatus, Integer code, String message, HttpServletRequest request) {
        ResponseVO<Void> entity = build();
        entity.setCode(code);
        entity.setHttpStatus(httpStatus);
        entity.setException(message);
        if (null != request) {
            entity.setPath(request.getRequestURI());
        }
        return entity;
    }

    public static <T> ResponseVO<T> fail(Integer code, String message) {
        ResponseVO<T> entity = new ResponseVO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseVO<T> fail(String message) {
        ResponseVO<T> entity = new ResponseVO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(Errors.SYSTEM_CUSTOM_ERROR.code);
        entity.setException(message);
        return entity;
    }

    public static <T> ResponseVO<T> fail(Errors errors) {
        ResponseVO<T> entity = new ResponseVO<T>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        entity.setCode(errors.code);
        entity.setException(errors.label);
        return entity;
    }

    private static ResponseVO<Void> build() {
        ResponseVO<Void> entity = new ResponseVO<Void>();
        entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        return entity;
    }

    public static ResponseVO<Void> fail(Integer httpStatus, Integer code, String message, HttpServletRequest request, Exception e) {
        ResponseVO<Void> entity = build();
        entity.setCode(code);
        entity.setHttpStatus(httpStatus);
        entity.setException(message);
        if (null != request) {
            entity.setPath(request.getRequestURI());
        }
        entity.setErrMsg(e.getMessage());
        return entity;
    }

    public static <T> ResponseVO<T> addMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_INSERT_FAIL) :
                ResponseBOUtil.success();
    }

    public static <T> ResponseVO<T> message(T data) {
        return data == null ?
                ResponseBOUtil.fail(Errors.SYSTEM_DATA_NOT_FOUND) :
                ResponseBOUtil.success(data);
    }

    public static <T> ResponseVO<T> updMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_UPDATE_ERROR) :
                ResponseBOUtil.success();
    }

    public static <T> ResponseVO<T> delMessage(int num) {
        return num == 0 ?
                ResponseBOUtil.fail(Errors.SYSTEM_DELETE_FAIL) :
                ResponseBOUtil.success();
    }
}
