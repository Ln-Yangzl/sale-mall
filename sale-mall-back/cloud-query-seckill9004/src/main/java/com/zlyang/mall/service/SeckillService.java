package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.Product;
import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.entities.SeckillProductDetail;
import com.zlyang.mall.mapper.SeckillMapper;
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

    public Seckill getSeckillById(Integer id){
        return seckillMapper.selectById(id);
    }

    public int createSeckill(Seckill seckill){
        return seckillMapper.insert(seckill);
    }

    public int deleteSeckillById(Integer seckillId){
        return seckillMapper.deleteById(seckillId);
    }

    public List<SeckillProductDetail> getAllSeckillsDetail(){
        List<Seckill> seckills = seckillMapper.selectList(null);
        CommonResult<List<LinkedHashMap>> commonResult = productFeignService.selectInIds(
                seckills.stream().map(i -> i.getProductId()).collect(Collectors.toList()));
        List<Product> products = (List<Product>) commonResult.getData()
                .stream()
                .map(item -> new Product((Integer) item.get("productId"),
                        (String) item.get("title"),
                        (Integer) item.get("inventory"),
                        (Integer) item.get("price"),
                        (String) item.get("disc"),
                        (String) item.get("pic")))
                .collect(Collectors.toList());

        return SeckillProductDetail.mergeProductsAndSeckills(products, seckills);
    }

}
