package com.springboot.framework.study.designpattern.sourcecode.spring;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 解释器模式
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/7 20:29
 */
public class Interpreter {
    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("10 * (2 + 1) * 1 + 66");
        int result = (int) expression.getValue();
        System.out.println(result);
    }
}
