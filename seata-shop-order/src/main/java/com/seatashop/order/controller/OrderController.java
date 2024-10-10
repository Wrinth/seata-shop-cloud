package com.seatashop.order.controller;

import com.seatashop.order.service.OrderService;
import com.seatashop.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /*
     * Get order by ID
     */
    @GetMapping("{id}")
    public Order findById(@PathVariable("id")Long id) {
        return orderService.findById(id);
    }
}
