package com.seatashop.user.controller;

import com.seatashop.pojo.User;
import com.seatashop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * Get user by ID
     */
    @GetMapping("{id}")
    public User findById(@PathVariable("id")Long id, HttpServletRequest request) {
        System.out.println("Authorization: " + request.getHeader("Authorization"));
        return userService.getById(id);
    }
}
