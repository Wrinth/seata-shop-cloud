package cn.mf5.order.client;

import cn.mf5.shop.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * name: 需要调用的服务名称（默认执行Ribbon负载均衡）
 * path: 接口的统一访问路径
 */
@FeignClient(name = "mafeng-user",path = "user",configuration = DefaultFeignConfiguration.class,fallback = UserClientFallback.class)
public interface UserClient {

    /**
     * 根据id查询用户
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id")Long id);

    /**
     * 扣减账户余额
     */
    @PutMapping("/deduct/{id}/{money}")
    public void deduct(@PathVariable("id") Long id,@PathVariable("money") Long money);
}
