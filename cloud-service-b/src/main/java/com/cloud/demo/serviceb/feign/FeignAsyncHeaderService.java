package com.cloud.demo.serviceb.feign;

import com.cloud.demo.serviceb.config.ThreadLocalCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FeignAsyncHeaderService
 * @Description 异步请求设置header头
 * @Author JackZhou
 * @Date 2021/2/1  15:16
 **/

@Service
@Slf4j
public class FeignAsyncHeaderService {

    @Autowired
    private ServiceaService service;

    public static final String HEAD_NAME = "testheader";

    @Async
    public void getById(String id, String testHead){
        log.info("当前处理线程 {}", Thread.currentThread().getName());
        try{
            TimeUnit.SECONDS.sleep(3);
            Map map = ThreadLocalCacheManager.getCache();
            if(map == null){
                map = new HashMap();
                ThreadLocalCacheManager.setCache(map);
            }
            map.put(HEAD_NAME, testHead);
            service.getById(id);
        }catch (Exception e){
            log.error("调用接口出错", e);
        }finally {
            ThreadLocalCacheManager.removeCache();
        }
    }


    @Async
    public void deleteById(String id, String token){
        try {
            TimeUnit.SECONDS.sleep(3);
            service.delete(id, token);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
