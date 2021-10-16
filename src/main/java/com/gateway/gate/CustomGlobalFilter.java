//package com.gateway.gate;
//
//import lombok.extern.slf4j.Slf4j;
//import org.bouncycastle.util.Strings;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicReference;
//
//
//@Order(1)
//@Component
//@Slf4j
//public class CustomGlobalFilter implements GlobalFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        Map<String, Object> body = exchange.getAttributes();
//        var request = exchange.getRequest().getBody();
//        log.info("Got here");
//        System.out.println("Filtering ++++++++++++++");
//        Object cachedBody = exchange.getAttribute("name");
//        log.info("Cached request body : {}", cachedBody);
//        return chain.filter(exchange);
//    }
//
//
//
//}
