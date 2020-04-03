package com.cloud.demo.eurekaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaaApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaaApp.class, args);
    }
}
