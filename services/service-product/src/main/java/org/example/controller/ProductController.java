package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    //查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        Product product =  productService.getProductById(productId);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @GetMapping("/readDB")
    public String readDB(){
        log.info("readDB...");
        return "readDB success...";
    }
}
