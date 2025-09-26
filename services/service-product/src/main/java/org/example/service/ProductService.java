package org.example.service;


import org.example.bean.Product;

public interface ProductService {
    //根据id获取商品
    Product getProductById(Long productId);
}
