package com.seatashop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seatashop.order.mapper.OrderMapper;
import com.seatashop.order.service.OrderService;
import com.seatashop.pojo.Order;
import com.seatashop.pojo.User;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.HttpEntity;
//import org.apache.hc.core5.http.ParseException;
//import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; // Get services info from register center

    @Override
    public Order findById(Long id) {
        // Get order by orderId
        Order order = getById(id);

        /* method 1
        // Create a Http Get Object
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:9001/user/" + order.getUserId();
        HttpGet httpGet = new HttpGet(url);

        // Get user by userId with http
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity);
            User user = JSON.parseObject(jsonStr, User.class);

            order.setUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
         */

        /* method 2
        String url = "http://localhost:9001/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
         */

        List<ServiceInstance> instances = discoveryClient.getInstances("seatashop-user");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);

        return order;
    }
}
