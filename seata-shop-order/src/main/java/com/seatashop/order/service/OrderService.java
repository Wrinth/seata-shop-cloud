package com.seatashop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seatashop.pojo.Order;

public interface OrderService extends IService<Order> {
    Order findById(Long id);
}
