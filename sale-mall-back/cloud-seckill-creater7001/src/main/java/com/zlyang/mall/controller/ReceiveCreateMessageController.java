package com.zlyang.mall.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: zlyang
 * @date: 2021-12-06 16:53
 * @description:
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveCreateMessageController {

    @StreamListener(Sink.INPUT)
    public void input(Message<Map<String, Integer>> message)
    {
        Map<String, Integer> payload = message.getPayload();
        payload.forEach((k, v) -> System.out.println(k+v));
    }
}
