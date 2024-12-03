package cn.mf5.user.controller;

import cn.mf5.shop.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    /**
     * 服务降级方法
     * @return
     */
    public User globalFallback(){
        User user = new User();
        user.setUsername("查无此人");
        return user;
    }
}
