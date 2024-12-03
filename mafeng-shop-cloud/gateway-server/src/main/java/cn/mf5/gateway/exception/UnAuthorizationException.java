package cn.mf5.gateway.exception;

import lombok.Getter;

/**
 * 自定义未授权异常类
 */
@Getter
public class UnAuthorizationException extends RuntimeException{
    private Integer status;//状态码

    public UnAuthorizationException(Integer stauts,String message){
        super(message);
        this.status = stauts;
    }
}
