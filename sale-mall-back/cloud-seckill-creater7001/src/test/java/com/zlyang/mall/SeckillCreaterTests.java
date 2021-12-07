package com.zlyang.mall;

import com.zlyang.mall.service.FullMsgSendService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-07 14:24
 * @description:
 */
@SpringBootTest
public class SeckillCreaterTests {

    @Resource
    private FullMsgSendService fullMsgSendService;

    @Test
    void sendFullMsgTest(){
        int i = fullMsgSendService.sendFullMsg(4);
        System.out.println(i);
    }
}
