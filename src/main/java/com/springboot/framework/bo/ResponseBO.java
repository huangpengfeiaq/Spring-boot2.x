package com.springboot.framework.bo;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * API请求的返回结果
 *
 * @author huangpengfei
 */
@Data
public class ResponseBO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;
    private int code;
    private int httpStatus;
    private String path;
    private Long timestamp;
    private String exception;
    /**
     * 联调阶段打印错误堆栈信息，方便快速定位异常
     */
    private String errMsg;

    /**
     * @param httpStatus HTTP状态码
     * @param code       业务代码
     * @param request    request对象
     * @return 响应体
     */
    public static <E> ResponseBO<E> build(Integer httpStatus, Integer code, HttpServletRequest request) {
        return build(httpStatus, code, null, null, request);
    }

    /**
     * @param httpStatus HTTP代码
     * @param code       业务代码
     * @param data       随错误响应体返回的相关错误数据。
     * @param request    request对象
     * @return 响应体
     */
    public static <E> ResponseBO<E> build(Integer httpStatus, Integer code, E data, HttpServletRequest request) {
        return build(httpStatus, code, null, data, request);
    }

    /**
     * @param httpStatus HTTP代码
     * @param code       业务代码
     * @param exception  异常对象
     * @param request    request对象
     * @return 响应体
     */
    public static <E> ResponseBO<E> build(Integer httpStatus, Integer code, Exception exception,
                                          HttpServletRequest request) {
        return build(httpStatus, code, exception, null, request);
    }

    /**
     * 创建响应体。
     *
     * @param httpStatus HTTP状态码
     * @param code       业务代码
     * @param data       伴随错误响应体一起返回的相关数据
     * @param exception  发生的异常信息
     * @param request    request对象
     * @return 响应体
     */
    public static <E> ResponseBO<E> build(Integer httpStatus, Integer code, Exception exception, E data,
                                          HttpServletRequest request) {
        ResponseBO<E> response = new ResponseBO<E>();
        response.data = data;
        if (null != exception) {
            response.exception = exception.getClass().getCanonicalName();
        }
        if (null != request) {
            response.path = request.getRequestURI();
        }
        response.httpStatus = httpStatus;
        response.code = code;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }
}
