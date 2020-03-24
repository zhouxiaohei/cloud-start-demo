//package com.cloud.demo.bootswagger;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName MyStartupRunner
// * @Author JackZhou
// * @Date 2020/3/22  14:15
// **/
//@Component
//@Order(1)
//@Slf4j
//public class MyStartupRunner implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            log.info("---CommandLineRunner 初始化开始----");
//            TimeUnit.SECONDS.sleep(30);
//            log.info("---CommandLineRunner 初始化结束----");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
