package com.cloud.demo.serviceb.controller;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.serviceb.feign.ServiceaFallbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FeignHystrixDemoController
 * @Author JackZhou
 * @Date 2020/3/18  17:04
 **/
@RestController
@RequestMapping("/demo/serviceb/feignHystrixDemo/")
@Slf4j
@Api(tags = "使用hystrix做feign的服务降级")
public class FeignHystrixDemoController {

    @Autowired
    private ServiceaFallbackService feignHystrixService;

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResponse<Person> getById(@PathVariable("id") String id){
        log.info("根据id {} 进行查询", id);
        return feignHystrixService.getById(id);
    }

}
