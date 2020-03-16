package com.cloud.demo.mybatisplus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncService
 * @Author JackZhou
 * @Date 2020/3/16  14:41
 **/
@Service
@Slf4j
public class AsyncService {

    @Async
    public void testAsync(String name){
        log.info("开始执行 {} 测试", name);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("结束执行 {} 测试", name);
    }


    public void testSync(String name){
        testAsync(name);
    }

    @Async
    public Future<String> taskA(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskA complete");
    }

    @Async
    public Future taskB(){
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskB complete");
    }

    @Async
    public Future taskC(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("taskC complete");
    }
}
