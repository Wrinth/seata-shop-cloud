package cn.mf5.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Feign请求拦截器
 */
@Component
public class FeignClientInterceptorConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //在请求头中添加授权信息
        requestTemplate.header("Authorization","admin");
    }
}
