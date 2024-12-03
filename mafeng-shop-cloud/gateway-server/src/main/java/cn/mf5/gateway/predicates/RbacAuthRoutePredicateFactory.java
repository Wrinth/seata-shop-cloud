package cn.mf5.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义权限模块的断言工厂
 */
@Component
public class RbacAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<RbacAuthRoutePredicateFactory.Config> {

    public RbacAuthRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("flag"); //flag是Config类的属性名称
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            if(config.getFlag().equals("rbac") && config.getName().equals("user")){
                //获取请求访问路径
                ServerHttpRequest request = exchange.getRequest();
                String path = request.getURI().getPath();
                if(path.startsWith("/user") ||
                   path.startsWith("/role") ||
                   path.startsWith("/permission") ||
                   path.startsWith("/menu")){
                    //代表符合断言规则，请求进行后续转发
                    return true;
                }
            }
            //不符合断言规则，不转发请求
            return false;
        };
    }


    /**
     * 定义Config静态内部类，用于接受配置传递的参数
     */
    public static class Config{
        private String flag;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
