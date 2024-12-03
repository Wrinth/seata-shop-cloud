package cn.mf5.order.service;

import cn.mf5.shop.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {

    Order findById(Long id);

    void saveOrder(Order order);
}
