package com.seatashop.order;

import com.seatashop.order.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.seatashop.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
public class SeataShopOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataShopOrderApplication.class, args);
    }

    /*
     * Init a rest template
     */
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        // return new RestTemplate();
//        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//    }
}
