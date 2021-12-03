package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.service.SeckillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:48
 * @description:
 */
@RestController
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @GetMapping("/seckill/get")
    public CommonResult getSeckillById(@RequestParam(name = "seckill_id") Integer id){
        Seckill seckillById = seckillService.getSeckillById(id);
        return CommonResult.success(seckillById);
    }

    @PostMapping("/seckill/create")
    public CommonResult createSeckill(@RequestBody Seckill seckill){
        int status = seckillService.createSeckill(seckill);
        if(status > 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
    }
}
