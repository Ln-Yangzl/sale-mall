package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.entities.SeckillOrder;
import com.zlyang.mall.mapper.SeckillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-06 14:33
 * @description:
 */
@Service
public class SeckillCreaterService {
    //TODO:开启全局事物支持

    @Resource
    private SeckillMapper seckillMapper;

    @Resource
    private OrderFeignService orderFeignService;

    public int createSeckill(int seckillId, int userId, int amount){
        Seckill seckill = seckillMapper.selectById(seckillId);
        int seckillInventory = seckill.getSeckillInventory();
        int productId = seckill.getProductId();
        // TODO:添加快速失败
        if(seckillInventory == 0){
            return ResultMsgEnum.FAIL.getCode();
        } else if(seckillInventory < amount){
            return ResultMsgEnum.FAIL.getCode();
        }
        setSeckillInventory(seckillId, seckillInventory - amount);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setProductId(productId);
        seckillOrder.setUserId(userId);
        seckillOrder.setAmount(amount);
        CommonResult orderResult = orderFeignService.createOrder(seckillOrder);
        if(orderResult.getCode()!= ResultMsgEnum.SUCCESS.getCode()){
            // TODO:全局回滚
            return ResultMsgEnum.FAIL.getCode();
        }
        return ResultMsgEnum.SUCCESS.getCode();
    }

    public int setSeckillInventory(int seckillId, int inventory){
        UpdateWrapper<Seckill> updateWrapper = Wrappers.update();
        updateWrapper.eq("seckill_id", seckillId);
        updateWrapper.set("seckill_inventory", inventory);
        return seckillMapper.update(null, updateWrapper);
    }
}
