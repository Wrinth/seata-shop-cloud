package cn.mf5.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 局部过滤器
 * 需求：限制指定IP才可以访问
 */
@Component
public class IPForbidGatewayFilterFactory extends AbstractGatewayFilterFactory<IPForbidGatewayFilterFactory.Config> {

    public IPForbidGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("permitIP");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            //获取用户请求的IP地址
            String ip = request.getRemoteAddress().getAddress().getHostAddress();

            if(config.getPermitIP().equals(ip)){
                //放行请求
                return chain.filter(exchange);
            }else{
                //拒绝请求
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
        };
    }

    /**
     * 定义Config静态内部类，用于接受配置传递的参数
     */
    public static class Config{
        private String permitIP;

        public String getPermitIP() {
            return permitIP;
        }

        public void setPermitIP(String permitIP) {
            this.permitIP = permitIP;
        }
    }
}
