package org.example.feign;

import org.example.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product",path = "api/product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {


    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);


}
