package com.cloud.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName GatewayApp
 * @Author JackZhou
 * @Date 2020/3/6  16:08
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
