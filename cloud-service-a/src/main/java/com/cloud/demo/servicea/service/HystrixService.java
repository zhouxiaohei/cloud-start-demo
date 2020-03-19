package com.cloud.demo.servicea.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName HystrixService
 * @Author JackZhou
 * @Date 2020/3/18  11:26
 **/
@Service
@Slf4j
public class HystrixService {

    @HystrixCommand(fallbackMethod = "simpleFallback")
    public String hystrixDemo(String name){
        log.info("接受到参数 {}", name);
        if(name.toLowerCase().contains("error")){
            throw new RuntimeException("出现异常" + name);
        }

        return "接收到参数：" + name ;
    }

    public String simpleFallback(String name){
        log.info("执行降级流程");
        return "对参数："+ name + "降级处理";
    }


}
