package com.zlyang.mall.service;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-04 14:12
 * @description:
 */
@Service
@FeignClient(value = "query-product")
public interface ProductFeignService {

    /**
     * 微服务调用，获取ids中的商品信息
     * @param ids
     * @return
     */
    @PostMapping("/product/selectInIds")
    public CommonResult<List<Product>> selectInIds(@RequestBody List<Integer> ids);

    /**
     * 微服务调用，获取id对应的product
     * CommonResult<Product>指定product, 可以避免java将其序列化为map, 序列化为指定对象
     * @param id productId
     * @return
     */
    @GetMapping("/product/get")
    public CommonResult<Product> getProductById(@RequestParam(name = "productId") Integer id);
}
