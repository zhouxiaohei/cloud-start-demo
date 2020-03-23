package com.cloud.demo.servicea.controller;

import com.cloud.demo.servicea.service.HystrixService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HystrixController
 * @Author JackZhou
 * @Date 2020/3/18  15:56
 **/
@RestController
@RequestMapping("/demo/servicea/hystrix")
@Slf4j
@Api(tags = "hystrix demo 管理")
public class HystrixController {

    @Autowired
    private HystrixService service;

    @ApiOperation(value = "简单的服务降级测试")
    @RequestMapping(value = "/simpleDemo", method = RequestMethod.GET)
    public String getById(@RequestParam("name") String name){
        return service.hystrixDemo(name);
    }

}
