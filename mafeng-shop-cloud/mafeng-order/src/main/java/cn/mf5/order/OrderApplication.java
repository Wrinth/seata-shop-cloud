package cn.mf5.order;

import cn.mf5.order.client.DefaultFeignConfiguration;
import cn.mf5.order.client.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 订单模块
 */
@SpringBootApplication
@MapperScan("cn.mf5.order.mapper")
@EnableDiscoveryClient // 启用服务注册
@EnableFeignClients  //开启Feign功能
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    /**
     * 初始化RestTemplate对象
     */
    /*@Bean
    @LoadBalanced // 添加负载均衡效果
    public RestTemplate restTemplate(){
        //return new RestTemplate();//默认JDK的URLConnection远程调用
        //return new RestTemplate(new HttpComponentsClientHttpRequestFactory());//切换为HttpClient
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());//切换为OKHttp
    }*/
}
