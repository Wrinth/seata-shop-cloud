package cn.mf5.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色模块
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    /**
     * 查询所有权限
     */
    @GetMapping("/list")
    public String list(){
        return "所有系统的角色列表";
    }

}
