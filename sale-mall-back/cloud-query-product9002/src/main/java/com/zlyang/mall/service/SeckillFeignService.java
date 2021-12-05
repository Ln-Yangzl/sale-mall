package com.zlyang.mall.service;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.Seckill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: zlyang
 * @date: 2021-12-05 10:02
 * @description:
 */
@Service
@FeignClient(value = "query-seckill")
public interface SeckillFeignService {

    /**
     * 创建一条秒杀条目
     * @param seckill
     * @return
     */
    @PostMapping("/seckill/create")
    public CommonResult createSeckill(@RequestBody Seckill seckill);
}
