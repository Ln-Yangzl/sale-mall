package com.zlyang.mall.controller;

import com.zlyang.mall.service.SeckillCreaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: zlyang
 * @date: 2021-12-06 16:53
 * @description:
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveCreateMessageController {

    @Resource
    private SeckillCreaterService seckillCreaterService;

    @StreamListener(Sink.INPUT)
    public void input(Message<Map<String, String>> message)
    {
        Map<String, String> payload = message.getPayload();
        log.info("receive kill:" + payload.toString());
        seckillCreaterService.createSeckill(
                Integer.parseInt(payload.get("seckillId")),
                Integer.parseInt(payload.get("userId")),
                Integer.parseInt(payload.get("amount")),
                payload.get("serial"));
    }
}
