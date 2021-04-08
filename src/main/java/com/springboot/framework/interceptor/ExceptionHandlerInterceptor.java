package com.springboot.framework.interceptor;

import com.springboot.framework.utils.ResponseVOUtil;
import com.springboot.framework.vo.ResponseVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常拦截器
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 22:37
 */
@ControllerAdvice
public class ExceptionHandlerInterceptor {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseVO<?> handler(RuntimeException e) {
        return ResponseVOUtil.fail(e.getMessage());
    }
}
