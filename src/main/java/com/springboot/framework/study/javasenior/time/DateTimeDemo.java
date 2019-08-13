package com.springboot.framework.study.javasenior.time;

import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/13 18:13
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.getTime());
    }
}
