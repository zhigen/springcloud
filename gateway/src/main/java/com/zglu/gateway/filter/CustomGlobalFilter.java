package com.zglu.gateway.filter;

import lombok.extern.java.Log;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Log
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(exchange.getRequest().getMethod() + " " + exchange.getRequest().getURI());
        log.info(getBodyFromRequest(exchange.getRequest()));
        boolean result = check(exchange);
        if (result) {
            return chain.filter(exchange);
        } else {
            //重写返回结果
            ServerHttpResponse response = exchange.getResponse();
            byte[] bits = "阻止访问".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
    }

    private String getBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取request body
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    private boolean check(ServerWebExchange exchange) {
        //校验权限
        String url = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();
        Map<String, String> headerMap = exchange.getRequest().getHeaders().toSingleValueMap();
        Map<String, String> paramMap = exchange.getRequest().getQueryParams().toSingleValueMap();
        return paramMap.get("token") != null;
    }
}