package cn.mf5.gateway.filter;

import cn.mf5.gateway.exception.UnAuthorizationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 统一鉴权过滤器（PRE过滤）
 */
@Component
//@Order(1) // 数字值越大，优先级越低的
public class AuthorizationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //需求：请求要求携带Authorization=admin，如果无此请求头，认为未登录或登录失败
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //获取请求头
        String authorization = request.getHeaders().getFirst("Authorization");

        if(StringUtils.isEmpty(authorization) || !"admin".equals(authorization)){
            //未登录或登录失败
            //写回401状态码
            //response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //结束请求
            //return response.setComplete();

            throw new UnAuthorizationException(401,"用户未授权");
        }

        //放行请求
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1; // 数字值越大，优先级越低的
    }
}
