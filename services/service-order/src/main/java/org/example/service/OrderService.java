package org.example.service;

import org.example.bean.Order;

public interface OrderService {
    //创建订单
    Order createOrder(Long productId, Long userId);
}
