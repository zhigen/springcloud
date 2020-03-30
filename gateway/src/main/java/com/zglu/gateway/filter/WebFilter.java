package com.zglu.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class WebFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            //重写url
            ServerHttpRequest request = exchange.getRequest();
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(exchange.getRequest().getURI().toString().replaceFirst("/flux", "")).build();
            request = request.mutate().uri(uri.toUri()).build();
            return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {

            }));
        });
    }
}