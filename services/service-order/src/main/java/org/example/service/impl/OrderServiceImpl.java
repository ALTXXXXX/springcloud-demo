package org.example.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.bean.Order;
import org.example.bean.Product;
import org.example.feign.ProductFeignClient;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder",blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
        //使用feign来完成远程调用
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        //总金额
        order.setTotalAmount( product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("尚硅谷");
        // 远程查询商品列表
        order.setProductList(Arrays.asList(product));
        return order;

    }
    //兜底回调
    public Order createOrderFallback(Long productId, Long userId, BlockException e){
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickName("未知用户");
        order.setAddress("未知信息："+e.getMessage());
        return order;
    }

    private Product getProductFromRemote(Long productId){
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
