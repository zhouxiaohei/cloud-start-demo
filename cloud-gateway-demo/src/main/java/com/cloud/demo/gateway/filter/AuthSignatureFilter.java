package com.cloud.demo.gateway.filter;

import com.cloud.demo.gateway.feign.CommonServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
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

    private static final String LOGIN_URL = "/api/v1/common/login";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().toString();
        if(!url.startsWith(LOGIN_URL)){
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (token == null || token.isEmpty()) {
                log.info( "X-SSO-FullticketId is empty..." );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

}
