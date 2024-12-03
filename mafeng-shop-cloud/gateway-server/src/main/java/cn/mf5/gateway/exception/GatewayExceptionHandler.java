package cn.mf5.gateway.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 定制网关异常出类
 */
@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Integer code = 0;
        String message = "";
        if(ex instanceof UnAuthorizationException){  //处理未授权异常
            UnAuthorizationException unAuthorizationException = (UnAuthorizationException)ex;
            code = unAuthorizationException.getStatus();
            message = unAuthorizationException.getMessage();
        }else{
            code = 500;
            message = "服务器繁忙，请稍后再试";
        }

        //写回json数据给前端
        //设置响应类，统一json格式
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        //封装错误信息
        AjaxResponse ajaxResponse = AjaxResponse.error(code,message);

        //写出json数据给前端
        return response.writeWith(Mono.fromSupplier(()->{
            DataBufferFactory factory = response.bufferFactory();
            byte[] bytes = null;
            try {
                bytes = objectMapper.writeValueAsBytes(ajaxResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return factory.wrap(bytes);
        }));
    }
}
