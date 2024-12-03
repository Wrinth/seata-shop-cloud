package cn.mf5.order.controller;

import cn.mf5.order.service.OrderService;
import cn.mf5.shop.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 根据id查询订单
     */
    @GetMapping("{id}")
    public Order findById(@PathVariable("id")Long id){
        return orderService.findById(id);
    }

    /**
     * 下订单
     */
    @PostMapping("/save")
    public String save(@RequestBody Order order){
        orderService.saveOrder(order);
        return "下单成功";
    }
}
