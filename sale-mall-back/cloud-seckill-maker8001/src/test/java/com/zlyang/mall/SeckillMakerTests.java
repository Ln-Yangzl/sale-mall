package com.zlyang.mall;

import com.zlyang.mall.service.SeckillRestrictorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-06 16:46
 * @description:
 */
@SpringBootTest
public class SeckillMakerTests {
    @Resource
    private SeckillRestrictorService seckillRestrictorService;

    @Test
    void testSend(){
        seckillRestrictorService.sendString();
    }
}
