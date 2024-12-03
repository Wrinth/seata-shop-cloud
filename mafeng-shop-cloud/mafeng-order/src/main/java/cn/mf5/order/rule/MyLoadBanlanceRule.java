package cn.mf5.order.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义负载均衡策略
 */
public class MyLoadBanlanceRule implements IRule {
    private ILoadBalancer lb;
    
    @Override
    public Server choose(Object o) {
        //获取所有可用服务列表
        List<Server> allServers = lb.getAllServers();
        //获取第一个有效服务
        Server server = allServers.get(0);
        return server;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
