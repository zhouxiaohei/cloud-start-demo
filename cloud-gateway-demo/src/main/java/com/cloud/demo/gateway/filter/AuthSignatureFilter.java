package com.cloud.demo.gateway.filter;

import com.cloud.demo.gateway.feign.CommonServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName AuthSignatureFilter
 * @Author JackZhou
 * @Date 2020/3/12  10:17
 **/
@Slf4j
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    @Autowired
    private CommonServiceService serviceService;

    private static final String LOGIN_URL = "/common/login";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().toString();
        if(!url.startsWith(LOGIN_URL)){
            String ticket = exchange.getRequest().getHeaders().getFirst("X-SSO-FullticketId");
            if (ticket == null || ticket.isEmpty()) {
                log.info( "X-SSO-FullticketId is empty..." );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            Object responese = serviceService.validate(ticket);
            log.info("请求成功结果：" + responese );
//            WebResponse responese = new WebResponse();
//            if(responese.getCode() != 200){
//                log.error( " 认证服务器校验 X-SSO-FullticketId：{} 失败 ", ticket);
//                DataBuffer responseBuff = exchange.getResponse().bufferFactory().wrap("123".getBytes());
//                return exchange.getResponse().writeWith(Flux.just(responseBuff));
//            }

            //new WebResponse<>(500, "认证服务器异常");
            ServerHttpRequest newRequest = exchange.getRequest().mutate().header("userInfo", "12345").build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

}
