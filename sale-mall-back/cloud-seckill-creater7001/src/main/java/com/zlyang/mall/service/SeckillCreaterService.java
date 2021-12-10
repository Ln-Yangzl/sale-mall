package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.entities.SeckillOrder;
import com.zlyang.mall.mapper.SeckillMapper;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Resource
    private FullMsgSendService fullMsgSendService;

    @GlobalTransactional(name = "create_seckill", rollbackFor = Exception.class)
    public int createSeckill(int seckillId, int userId, int amount){
        // 如果已经在消息队列中发送过已满消息，则不再向数据库查询，直接返回
        if(fullMsgSendService.isFull(seckillId)){
            return ResultMsgEnum.FAIL.getCode();
        }
        Seckill seckill = seckillMapper.selectById(seckillId);
        int seckillInventory = seckill.getSeckillInventory();
        int productId = seckill.getProductId();
        // 库存已空，发送已满消息给maker/restrictor，不再允许用户进入，maker/restrictor快速返回
        if(seckillInventory == 0){
            fullMsgSendService.sendFullMsg(seckillId);
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
