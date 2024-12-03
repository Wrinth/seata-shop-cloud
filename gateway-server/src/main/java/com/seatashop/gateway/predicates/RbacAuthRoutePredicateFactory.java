package com.seatashop.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RbacAuthRoutePredicateFactory extends
        AbstractRoutePredicateFactory<RbacAuthRoutePredicateFactory.Config> {

    public RbacAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("flag");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> { //获取访问路径
            String uri = exchange.getRequest().getURI().getPath();
            if(config.getFlag().equals("rbac")
                    && ( uri.startsWith("/user")
                    ||uri.startsWith("/role")
                    ||uri.startsWith("/permission")
                    ||uri.startsWith("/menu"))
            ) {
                return true;//匹配成功
            }
            return false; //匹配失败
        };
    }

    public static class Config {
        private String flag;
        public String getFlag() {
            return flag;
        }
        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
