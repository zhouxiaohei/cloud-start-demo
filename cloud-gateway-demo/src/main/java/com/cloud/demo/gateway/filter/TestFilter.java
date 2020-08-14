package com.cloud.demo.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @ClassName TestFilter
 * @Author JackZhou
 * @Date 2020/8/14  14:20
 * @Description  需要根据请求中不同的参数，类似applyNo将请求转发到不同的微服务处理
 *               但是如果走到了断言阶段predicates，说明已经决定转发到那个微服务上了；
 *               我Debug过此时再去修改处理的微服务等等信息以及不太可能，对应的服务标记和过滤器已经加装完成
 *
 *               所以要在predicates之前将url处理，以便能转发到不同的微服务；并借助网关的能力
 *               在SpringMVC中有拦截器、过滤器等
 *               SpringMVC作为同步servlet处理器   webflux 作为异步的 https://juejin.im/post/6844904035519037447 SpringMVC和webflux
 *               在gateway中用的是webflux  过滤器WebFilter
 **/

@Component
public class TestFilter implements WebFilter {

    public static final String TEST_URL = "/api/v1/test";

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        String url = serverWebExchange.getRequest().getPath().toString();
        if(url.startsWith(TEST_URL)){
            ServerHttpRequest newRequest = serverWebExchange.getRequest().mutate().path("/api/v1/mybatisplus/demo/get2/123").build();
            ServerWebExchange exChange = serverWebExchange.mutate().request(newRequest).build();
            return webFilterChain.filter(exChange);
        }
        return webFilterChain.filter(serverWebExchange);
    }

}
