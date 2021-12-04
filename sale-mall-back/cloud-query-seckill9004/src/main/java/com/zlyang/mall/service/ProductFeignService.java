package com.zlyang.mall.service;

import com.zlyang.mall.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-04 14:12
 * @description:
 */
@Service
@FeignClient(value = "query-product")
public interface ProductFeignService {

    /**
     * 微服务调用，获取ids中的商品信息
     * @param ids
     * @return
     */
    @PostMapping("/product/selectInIds")
    public CommonResult selectInIds(@RequestBody List<Integer> ids);
}
