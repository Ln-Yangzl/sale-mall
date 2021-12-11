package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.SeckillOrder;
import com.zlyang.mall.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:18
 * @description:
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    public SeckillOrder getOrderById(Integer id){
        return orderMapper.selectById(id);
    }

    public int createOrder(SeckillOrder seckillOrder){
        return orderMapper.insert(seckillOrder);
    }

    public int getOrderBySerial(String serial){
        QueryWrapper<SeckillOrder> query = Wrappers.query();
        query.select("order_id");
        query.eq("serial", serial);
        SeckillOrder seckillOrder = orderMapper.selectOne(query);
        return seckillOrder.getOrderId();
    }
}
