package com.seatashop.order.client;

import com.seatashop.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "seatashop-user", path = "user")
public interface UserClient {

    @GetMapping("{id}")
    public User findById(@PathVariable("id")Long id);
}
