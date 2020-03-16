package com.cloud.demo.bootswagger;

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
public class BootSwaggerApp {
    public static void main(String[] args) {
        SpringApplication.run(BootSwaggerApp.class, args);
    }
}
