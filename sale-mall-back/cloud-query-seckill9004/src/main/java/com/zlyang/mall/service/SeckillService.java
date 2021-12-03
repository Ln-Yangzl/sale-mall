package com.zlyang.mall.service;

import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.mapper.SeckillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:46
 * @description:
 */
@Service
public class SeckillService {

    @Resource
    private SeckillMapper seckillMapper;

    public Seckill getSeckillById(Integer id){
        return seckillMapper.selectById(id);
    }

    public int createSeckill(Seckill seckill){
        return seckillMapper.insert(seckill);
    }
}
