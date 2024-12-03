package cn.mf5.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 用户模块
 */
@SpringBootApplication
@MapperScan("cn.mf5.user.mapper")
//@EnableEurekaClient
@EnableDiscoveryClient  //开启服务注册
//@EnableCircuitBreaker
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
