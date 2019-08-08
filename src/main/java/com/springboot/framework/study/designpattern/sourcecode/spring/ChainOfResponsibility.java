package com.springboot.framework.study.designpattern.sourcecode.spring;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 职责链模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/8 13:05
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        // 说明
        // protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {...}
        // HandlerExecutionChain mappedHandler = null;
        // mappedHandler = this.getHandler(processedRequest);
        // 在 mappedHandler.applyPreHandle 内部得到了 HandlerInterceptor interceptor
        // 调用了拦截器 interceptor.preHandle 方法
        // if (!mappedHandler.applyPreHandle(processedRequest, response)) {
        //                        return;
        //                    }
        // ...
        DispatcherServlet dispatcherServlet;
    }
}
