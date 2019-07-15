package com.springboot.framework.interceptor;

import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.utils.ResponseBOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 22:37
 */
@ControllerAdvice
public class ExceptionHandlerInterceptor {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseVO handler(RuntimeException e) {
        return ResponseBOUtil.fail(e.getMessage());
    }
}