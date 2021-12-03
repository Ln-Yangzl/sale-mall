package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.SeckillOrder;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:19
 * @description:
 */
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("/order/get")
    public CommonResult getOrderById(@RequestParam(value = "orderId") Integer id){
        SeckillOrder seckillOrder = orderService.getOrderById(id);
        return CommonResult.success(seckillOrder);
    }

    @PostMapping("/order/create")
    public CommonResult createOrder(@RequestBody SeckillOrder seckillOrder){
        int status = orderService.createOrder(seckillOrder);
        if(status > 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
    }
}
