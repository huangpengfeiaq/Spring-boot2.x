//package com.springboot.framework;
//
//import com.springboot.framework.rabbit.Sender;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Date;
//
///**
// * @author huangpengfei
// * @version 1.0
// * @date 2019/7/21 12:05
// */
//@RunWith(value= SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ProjectApplication.class)
//public class RabbitTests {
//
//    @Autowired
//    private Sender sender;
//
//    @Test
//    public void sendTest() throws Exception {
//        while(true){
//            String msg = new Date().toString();
//            sender.send(msg);
//            Thread.sleep(1000);
//        }
//    }
//}