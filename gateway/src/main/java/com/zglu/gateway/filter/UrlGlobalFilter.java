package com.zglu.gateway.filter;

import com.zglu.gateway.config.MySwaggerResourceProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

/**
 * 服务地址拦截器
 *
 * @author zglu
 */
@Log4j2
@Component
public class UrlGlobalFilter implements GlobalFilter, Ordered {

    public static final Pattern PATTERN_UPPERCASE = Pattern.compile("/first|/second");

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getURI().getPath().endsWith(MySwaggerResourceProvider.SWAGGER2URL)) {
            // 当访问路径为接口文档时，将路由名称去除
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(PATTERN_UPPERCASE.matcher(exchange.getRequest().getURI().toString()).replaceFirst("")).build();
            ServerHttpRequest request = exchange.getRequest();
            request = request.mutate().uri(uri.toUri()).build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        return chain.filter(exchange);
    }
}