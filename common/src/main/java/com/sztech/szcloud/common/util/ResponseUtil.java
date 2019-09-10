package com.sztech.szcloud.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 响应处理工具
 */
public class ResponseUtil {

    public static Mono<Void> jsonResponse(ServerHttpResponse response, Map<String, Object> responseData) {
        String json = JSON.toJSONString(responseData);
        byte[] data = json.getBytes();
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }
}
