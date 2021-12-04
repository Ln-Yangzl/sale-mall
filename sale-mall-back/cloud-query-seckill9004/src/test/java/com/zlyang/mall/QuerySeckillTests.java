package com.zlyang.mall;

import com.zlyang.mall.entities.Seckill;
import com.zlyang.mall.entities.SeckillProductDetail;
import com.zlyang.mall.mapper.SeckillMapper;
import com.zlyang.mall.service.SeckillService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-04 15:19
 * @description:
 */
@SpringBootTest
public class QuerySeckillTests {

    @Resource
    SeckillMapper seckillMapper;

    @Resource
    SeckillService seckillService;

    @Test
    void testSelectList(){
        List<Seckill> seckills = seckillMapper.selectList(null);
        seckills.forEach(System.out::println);
    }

    @Test
    void testMergeList(){
        List<SeckillProductDetail> allSeckillsDetail = seckillService.getAllSeckillsDetail();
        allSeckillsDetail.forEach(System.out::println);
    }
}
