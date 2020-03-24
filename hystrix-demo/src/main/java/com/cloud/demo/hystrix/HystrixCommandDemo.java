package com.cloud.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @date: 16:40 2018/11/1
 */
public class HystrixCommandDemo extends HystrixCommand<String> {

    private String input;


    /**
     *   GroupKey：该命令属于哪一个组，可以帮助我们更好的组织命令。
         CommandKey：该命令的名称
         ThreadPoolKey：该命令所属线程池的名称，同样配置的命令会共享同一线程池，若不配置，会默认使用GroupKey作为线程池名称。
         CommandProperties：该命令的一些设置，包括断路器的配置，隔离策略，降级设置，以及一些监控指标等。
         ThreadPoolProerties：关于线程池的配置，包括线程池大小，排队队列的大小等。
     */
    protected HystrixCommandDemo(String input) {
        //设置HystrixCommand的属性
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixCommandDemoGroup"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HystrixCommandDemoPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10))
        );
        this.input = input;
    }

    @Override
    protected String run() throws Exception {
        return "接收请求：" + input;
    }

    //服务降级
    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }


    public static void main(String[] args) {
        List<String> objects = Collections.emptyList();

        // List<String> aa = null;
        System.out.println(2222);
        for (String str : objects) {
            System.out.println("---" + str);
        }
        System.out.println(1111);
    }
}
