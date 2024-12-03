package cn.mf5.user.controller;

import cn.mf5.shop.pojo.User;
import cn.mf5.user.service.UserService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 */
@RestController
@RequestMapping("user")
@RefreshScope  //实时刷新配置信息
public class UserController{
    @Autowired
    private UserService userService;

    @Value("${mafeng.password}")
    private String password;

    /**
     * 根据id查询用户
     */
    @GetMapping("{id}")
    @SentinelResource("hotUser")
    public User findById(@PathVariable("id")Long id, HttpServletRequest request,String name){
        /*System.out.println("授权信息："+request.getHeader("Authorization"));
        System.out.println("name："+request.getParameter("name"));
        System.out.println("name："+name);*/

        if(id==1){
            throw new RuntimeException("模拟异常发生");
            /*try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        System.out.println("password="+password);
        return userService.getById(id);
    }

    /**
     * 修改用户
     */
    @GetMapping("/update")
    public String update(){
        userService.queryUserByName();
        return "修改用户成功";
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete")
    public String delete(){
        userService.queryUserByName();
        return "删除用户成功";
    }

    /**
     * 扣减账户余额
     */
    @PutMapping("/deduct/{id}/{money}")
    public void deduct(@PathVariable("id") Long id,@PathVariable("money") Long money){
        //userService.deduct(id,money);
        userService.deductTCC(id,money);
    }
}
