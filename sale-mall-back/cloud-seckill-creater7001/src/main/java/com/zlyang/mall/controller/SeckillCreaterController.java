package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.service.SeckillCreaterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-06 14:50
 * @description:
 */
@RestController
public class SeckillCreaterController {

    @Resource
    SeckillCreaterService seckillCreaterService;

    @PostMapping("/create/makeSeckill")
    public CommonResult makeSeckill(@RequestParam("seckillId") int seckillId,
                                    @RequestParam("userId") int userId,
                                    @RequestParam("amount")int amount,
                                    @RequestParam("serial")String serial){
        int status = seckillCreaterService.createSeckill(seckillId, userId, amount, serial);
        if(status != ResultMsgEnum.SUCCESS.getCode()){
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
        return CommonResult.success(null);
    }
}
