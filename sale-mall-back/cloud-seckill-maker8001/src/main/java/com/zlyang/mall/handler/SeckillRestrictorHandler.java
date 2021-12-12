package com.zlyang.mall.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.ResultMsgEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zlyang
 * @date: 2021-12-12 14:16
 * @description:
 */
public class SeckillRestrictorHandler {
    public static CommonResult handleCreateSeckill(@PathVariable("id") Integer seckillId,
                                                   @RequestParam("userId") Integer userId,
                                                   @RequestParam("amount") Integer amount,
                                                   @RequestParam("serial") String serial,
                                                   BlockException exception){
        return CommonResult.error(ResultMsgEnum.SECKILL_FULL);
    }
}
