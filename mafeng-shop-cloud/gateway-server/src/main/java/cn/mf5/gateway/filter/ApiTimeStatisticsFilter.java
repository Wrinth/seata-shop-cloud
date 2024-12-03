package cn.mf5.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 统计每个接口的执行时长过滤器
 */
@Component
@Order(2)
@Slf4j
public class ApiTimeStatisticsFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //在转发请求之前（pre)获取时间
        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            //获取response的执行时间
            long endTime = System.currentTimeMillis();

            log.info("uri："+exchange.getRequest().getURI().getPath()+",cost time："+(endTime-startTime)+"ms");
        }));
    }
}
