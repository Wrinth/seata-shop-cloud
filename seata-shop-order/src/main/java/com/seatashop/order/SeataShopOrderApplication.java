package com.seatashop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.seatashop.order.mapper")
@EnableDiscoveryClient
public class SeataShopOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataShopOrderApplication.class, args);
    }

    /*
     * Init a rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
