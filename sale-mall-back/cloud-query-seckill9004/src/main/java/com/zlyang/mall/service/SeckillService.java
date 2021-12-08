package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.*;
import com.zlyang.mall.exception.ServiceException;
import com.zlyang.mall.mapper.SeckillMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:46
 * @description:
 */
@Service
public class SeckillService {

    @Resource
    private SeckillMapper seckillMapper;

    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private SeckillMessageService seckillMessageService;

    @Cacheable(value = "short-cache", key = "'getSeckillById'+#id", unless = "#result == null")
    public Seckill getSeckillById(Integer id){
        return seckillMapper.selectById(id);
    }

    @Cacheable(value = "short-cache", key = "'getSeckillDetailById'+#id", unless = "#result == null")
    public SeckillProductDetail getSeckillDetailById(Integer seckillId){
        Seckill seckill = getSeckillById(seckillId);
        CommonResult result = productFeignService.getProductById(seckill.getProductId());
        if(result.getCode() != ResultMsgEnum.SUCCESS.getCode()){
            throw new ServiceException(
                    ResultMsgEnum.SERVICE_CALL_FAIL.getCode(),
                    ResultMsgEnum.SERVICE_CALL_FAIL.getMessage()
                            + "调用服务: productFeignService.getProductById"
                            + "调用参数：" + seckill.getProductId());
        }
        Product product = (Product) result.getData();
        return new SeckillProductDetail(product, seckill);
    }

    @CacheEvict(value = "long-cache", key = "'getAllSeckillsDetail'")
    public int createSeckill(Seckill seckill){
        int insert = seckillMapper.insert(seckill);
        seckillMessageService.sendSeckillRestrictionMsg(
                seckill.getSeckillId(),
                seckill.getSeckillInventory(),
                seckill.getEndTime()
        );
        return insert;
    }

    @CacheEvict(value = "long-cache", key = "'getAllSeckillsDetail'")
    public int deleteSeckillById(Integer seckillId){
        return seckillMapper.deleteById(seckillId);
    }

    @Cacheable(value = "long-cache", key = "'getAllSeckillsDetail'", unless = "#result == null")
    public List<SeckillProductDetail> getAllSeckillsDetail(){
        List<Seckill> seckills = seckillMapper.selectList(null);
        CommonResult<List<Product>> result = productFeignService.selectInIds(seckills.
                stream()
                .map(i -> i.getProductId())
                .collect(Collectors.toList()));
        if(result.getCode()!=ResultMsgEnum.SUCCESS.getCode()){
            throw new ServiceException(
                    ResultMsgEnum.SERVICE_CALL_FAIL.getCode(),
                    ResultMsgEnum.SERVICE_CALL_FAIL.getMessage() + "调用服务:productFeignService.selectInIds");
        }

        return SeckillProductDetail.mergeProductsAndSeckills(result.getData(), seckills);
    }

}
