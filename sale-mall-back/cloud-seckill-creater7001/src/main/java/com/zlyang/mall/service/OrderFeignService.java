package com.zlyang.mall.service;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.Product;
import com.zlyang.mall.entities.SeckillOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-06 14:30
 * @description:
 */
@Service
@FeignClient(value = "query-order")
public interface OrderFeignService {

    /**
     * 创建一个订单
     * @param seckillOrder
     * @return
     */
    @PostMapping("/order/create")
    public CommonResult createOrder(@RequestBody SeckillOrder seckillOrder);
}
