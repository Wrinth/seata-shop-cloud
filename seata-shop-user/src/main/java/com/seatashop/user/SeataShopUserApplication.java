package com.seatashop.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.seatashop.user.mapper")
@EnableDiscoveryClient
public class SeataShopUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataShopUserApplication.class, args);
    }
}
