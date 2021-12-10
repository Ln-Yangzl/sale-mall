package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.service.SeckillRestrictorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-06 15:53
 * @description:
 */
@RestController
public class SeckillRestrictorController {

    @Resource
    private SeckillRestrictorService seckillRestrictorService;

    @PostMapping("/create/seckill")
    public CommonResult createSeckill(@RequestParam("seckillId") Integer seckillId,
                                      @RequestParam("userId") Integer userId,
                                      @RequestParam("amount") Integer amount,
                                      @RequestParam("serial") String serial){
        ResultMsgEnum i = seckillRestrictorService.sendSeckillMessage(seckillId, userId, amount, serial);
        if(i.getCode() == ResultMsgEnum.SUCCESS.getCode()){
            return CommonResult.success(null);
        } else{
            return CommonResult.error(i);
        }
    }
}
