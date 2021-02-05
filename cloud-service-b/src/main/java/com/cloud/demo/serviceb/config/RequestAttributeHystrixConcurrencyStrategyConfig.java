package com.cloud.demo.serviceb.config;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RequestAttributeHystrixConcurrencyStrategyConfig
 * @Author JackZhou
 * @Date 2021/2/4  17:59
 **/

@Configuration
@ConditionalOnClass({HystrixCommand.class})
@ConditionalOnProperty(
        value = {"feign.hystrix.enabled"},
        matchIfMissing = true
)
public class RequestAttributeHystrixConcurrencyStrategyConfig {

    public RequestAttributeHystrixConcurrencyStrategyConfig() {

    }

    @Bean
    RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
        System.out.println("~实例化hystrix相关处理类~");
        return new RequestAttributeHystrixConcurrencyStrategy();
    }
}
