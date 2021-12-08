package com.zlyang.mall.controller;

import com.zlyang.mall.stream.SeckillSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: zlyang
 * @date: 2021-12-08 14:18
 * @description:
 */
@Slf4j
@EnableBinding(SeckillSink.class)
public class ReceiveMsgController {

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @StreamListener(SeckillSink.FULL_MSG)
    public void receiveFullMsg(Message<String> message){
        String payload = message.getPayload();
        log.info("Get full msg: " + payload);
        valueOperations.set(payload + "lock", "full");
    }

    @StreamListener(SeckillSink.CREATE_MSG)
    public void receiveCreateMsg(Message<Map<String, String>> message){
        Map<String, String> payload = message.getPayload();
        log.info("Get create seckill msg: " + payload.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = null;
        try {
            end = dateFormat.parse(payload.get("expire"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = (end.getTime() - System.currentTimeMillis())/1000;
        log.info("Time expire:" + diff);
        valueOperations.set(payload.get("seckillId") + "count", Integer.parseInt(payload.get("amount")), diff, TimeUnit.SECONDS);
    }
}
