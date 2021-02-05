package com.cloud.demo.serviceb.config;

import com.cloud.demo.serviceb.feign.FeignAsyncHeaderService;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
@Slf4j
public class FeignClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            //todo  在service层处理的线程和当前的不是一个  不启用hystrix可以
            // FeignAsyncHeaderService    : 当前处理线程 task-1   FeignClientConfiguration  : 当前处理线程 hystrix-service-a-1

            log.info("当前处理线程 {}", Thread.currentThread().getName());
            Map cache = ThreadLocalCacheManager.getCache();
            if(cache != null){
                Object headValue = cache.get(FeignAsyncHeaderService.HEAD_NAME);
                if(headValue != null){
                    requestTemplate.header(FeignAsyncHeaderService.HEAD_NAME, (String)headValue );
                    return;
                }
            }

            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                requestTemplate.header(FeignAsyncHeaderService.HEAD_NAME, "testheaderDemo");
            }

        };
    }
}

