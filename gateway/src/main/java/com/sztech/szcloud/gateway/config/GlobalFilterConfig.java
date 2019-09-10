package com.sztech.szcloud.gateway.config;

import com.google.common.collect.Maps;
import com.sztech.szcloud.common.util.ResponseUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.Map;

@Configuration

public class GlobalFilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter authFilter(){
        return (exchange, chain) -> {
            String token = exchange.getRequest().getQueryParams().getFirst("token");
            if (token == null || token.isEmpty()) {
                ServerHttpResponse response = exchange.getResponse();
                // 封装错误信息
                Map<String, Object> responseData = Maps.newHashMap();
                responseData.put("code", 401);
                responseData.put("message", "非法请求");
                responseData.put("cause", "Token is empty");
                return ResponseUtil.jsonResponse(response, responseData);
            }
            return chain.filter(exchange);
        };
    }
}
