package com.cloud.demo.bootswagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName BootSwaggerApp
 * @Author JackZhou
 * @Date 2020/3/5  11:25
 **/
@SpringBootApplication
@EnableCaching
@Slf4j
public class BootSwaggerApp {
    public static void main(String[] args) {
        SpringApplication.run(BootSwaggerApp.class, args);
    }

//    @PostConstruct
//    public void testPostConstruct(){
//        try {
//            log.info("---PostConstruct 初始化开始----");
//            TimeUnit.SECONDS.sleep(30);
//            log.info("---PostConstruct 初始化结束----");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
