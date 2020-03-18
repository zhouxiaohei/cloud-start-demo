package com.cloud.demo.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServiceaApp
 * @Author JackZhou
 * @Date 2020/3/16  17:40
 **/
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ServiceaApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceaApp.class, args);
    }
}
