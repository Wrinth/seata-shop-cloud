package cn.mf5.order.client;

import cn.mf5.shop.pojo.User;
import org.springframework.stereotype.Component;

/**
 * Fallback降级处理
 */
@Component
public class UserClientFallback implements UserClient{
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setUsername("查询不到此人");
        return user;
    }

    @Override
    public void deduct(Long id, Long money) {

    }
}
