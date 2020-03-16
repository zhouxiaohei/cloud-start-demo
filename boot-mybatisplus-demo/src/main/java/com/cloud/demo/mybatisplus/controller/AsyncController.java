package com.cloud.demo.mybatisplus.controller;

import com.cloud.demo.mybatisplus.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName AsyncController
 * @Author JackZhou
 * @Date 2020/3/16  14:40
 **/
@Controller
@RequestMapping("/demo/mybatisplus/")
@Slf4j
@Api(tags= "异步测试cotroller")
public class AsyncController {

    @Autowired
    AsyncService service;

    @ApiOperation("测试异步")
    @RequestMapping(value = "/testAsync", method = RequestMethod.GET)
    @ResponseBody
    public String testAsync(@RequestParam String name){
        service.testAsync(name);
        return  name + "success";
    }

    @ApiOperation("测试同步")
    @RequestMapping(value = "/testSync", method = RequestMethod.GET)
    @ResponseBody
    public String testSync(@RequestParam String name){
        service.testSync(name);
        return  name + "success";
    }

    @ApiOperation("测试异步并发执行")
    @RequestMapping(value = "/testAsyncList", method = RequestMethod.GET)
    @ResponseBody
    public List<String> testAsyncList() {

        Long begin = System.currentTimeMillis() / 1000;
        List list = new ArrayList();
        Future<String> taskA = service.taskA();
        Future<String> taskB = service.taskB();
        Future<String> taskC = service.taskC();
        try {
            log.info("taskA 是否完成 {}", taskA.isDone());
            list.add(taskA.get());
            log.info("taskA 是否完成 {}", taskA.isDone());
            list.add(taskB.get());
            list.add(taskC.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis() / 1000;
        list.add(" cost time: " + (end - begin));
        return list;
    }

}
