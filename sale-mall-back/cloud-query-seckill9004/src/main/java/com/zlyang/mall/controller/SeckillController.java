package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.entities.SeckillProductDetail;
import com.zlyang.mall.service.SeckillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult getSeckillById(@RequestParam(name = "seckillId") Integer id){
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

    @GetMapping("/seckill/getAll")
    public CommonResult getAll(){
        List<SeckillProductDetail> allSeckillsDetail = seckillService.getAllSeckillsDetail();
        return CommonResult.success(allSeckillsDetail);
    }

    @GetMapping("/seckill/deleteById")
    public CommonResult deleteSeckillById(@RequestParam(name = "seckillId") Integer seckillId){
        int status = seckillService.deleteSeckillById(seckillId);
        if(status > 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
    }
}
