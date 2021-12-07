package com.zlyang.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-07 14:27
 * @description:
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveFullMsgController {

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @StreamListener(Sink.INPUT)
    public void receiveFullMsg(Message<String> message){
        String payload = message.getPayload();
        log.info("Get full msg: " + payload);
        valueOperations.set(payload, "full");
    }
}
