package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.Order;
import org.example.properties.OrderProperties;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//@RefreshScope
@RestController
//@RequestMapping("/api/order")
@Slf4j
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;
    //订单创建?
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId")Long userId,
                             @RequestParam("productId")Long productId){
      Order order =  orderService.createOrder(productId,userId);
        return order;
    }

    @GetMapping("/seckill")
    public Order seckill(@RequestParam("userId")Long userId,
                             @RequestParam("productId")Long productId){
        Order order =  orderService.createOrder(productId,userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }


    @GetMapping("/config")
    public String config(){
        return orderProperties.getTimeout() + "," + orderProperties.getAutoConfirm()+","+orderProperties.getDbUrl();
    }

    @GetMapping("/readDB")
    public String readDB(){
      log.info("readDB...");
      return "readDB success...";
    }

}
