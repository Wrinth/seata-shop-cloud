package cn.mf5.order.client;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * 修改OpenFeign的参数
 */
public class DefaultFeignConfiguration {

    /**
     * 修改日志级别
     */
    @Bean
    public Logger.Level configLevel(){
        return Logger.Level.FULL;
    }

    /**
     * 开启Feign的重试策略
     */
    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }
}
