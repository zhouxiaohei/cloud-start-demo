package com.cloud.demo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName MybatisPlusApp
 * @Author JackZhou
 * @Date 2020/3/5  15:43
 **/
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.cloud.demo.mybatisplus")
@MapperScan("com.cloud.demo.mybatisplus.dao.mapper")
@EnableAsync
public class MybatisPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
    }
}
