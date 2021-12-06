package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.service.SeckillRestrictorService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/create/seckill")
    public CommonResult createSeckill(@RequestParam("seckillId") Integer seckillId,
                                      @RequestParam("userId") Integer userId,
                                      @RequestParam("amount") Integer amount){
        int i = seckillRestrictorService.sendSeckillMessage(seckillId, userId, amount);
        return CommonResult.success(i);
    }
}
