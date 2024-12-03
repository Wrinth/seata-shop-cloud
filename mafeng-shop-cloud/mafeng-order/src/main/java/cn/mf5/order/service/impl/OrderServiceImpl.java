package cn.mf5.order.service.impl;

import cn.mf5.order.client.UserClient;
import cn.mf5.order.mapper.OrderMapper;
import cn.mf5.order.service.OrderService;
import cn.mf5.shop.pojo.Order;
import cn.mf5.shop.pojo.User;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
   /* @Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private DiscoveryClient discoveryClient;//用于从注册中心服务端拉取服务信息对象

    @Autowired
    private UserClient userClient;

    @Override
    public Order findById(Long id) {
        //根据id查询订单数据
        Order order = getById(id);
        //根据userId查询用户 (远程调用完成用户信息获取？？？)

        //方式一：使用HttpClient实现远程调用
        //创建HttpClient对象
       /* CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建Get请求
        String url = "http://localhost:9001/user/"+order.getUserId();
        HttpGet httpGet = new HttpGet(url);

        //发出请求，获取响应
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);

            //获取响应体内容
            HttpEntity entity = response.getEntity();

            //把响应体转换为字符串
            String json = EntityUtils.toString(entity);

            //将json转化为User对象
            User user = JSON.parseObject(json, User.class);

            //赋值给订单
            order.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //方式二：使用RestTemplate实现远程调用
       /* String url = "http://localhost:9001/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);*/

        //方式三：使用DiscoveryClient拉取服务信息 + RestTemplate实现远程调用
        /*List<ServiceInstance> instances = discoveryClient.getInstances("mafeng-user");
        //因为只有一个user服务，取出第一个即可
        ServiceInstance serviceInstance = instances.get(0);
        //获取IP地址
        String host = serviceInstance.getHost();
        //获取port号
        int port = serviceInstance.getPort();

        String url = "http://"+host+":"+port+"/user/"+order.getUserId();*/

        //方式四：RestTemplate + @LoadBalance 负载均衡
       /* String url = "http://mafeng-user/user/"+order.getUserId();  // mafeng-user是serviceName

        System.out.println("url="+url);
        User user = restTemplate.getForObject(url, User.class);*/

        //方式五：OpenFeign 远程调用
        User user = userClient.findById(order.getUserId());
        order.setUser(user);


        return order;
    }

    @Override
    //@Transactional // 本地事务
    @GlobalTransactional(rollbackFor = Exception.class)
    public void saveOrder(Order order) {
        //保存订单信息
        save(order);

        //扣减用户余额
        userClient.deduct(order.getUserId(),order.getPrice()*order.getNum());

        //模拟发生异常
       // int i = 100/0;
    }
}
