package cn.mf5.gateway.exception;

import lombok.Data;

@Data
public class AjaxResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public static AjaxResponse error(Integer code,String message){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(code);
        ajaxResponse.setMessage(message);
        return ajaxResponse;
    }

    public static AjaxResponse error(Integer code,String message,Object data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(code);
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(data);
        return ajaxResponse;
    }
}