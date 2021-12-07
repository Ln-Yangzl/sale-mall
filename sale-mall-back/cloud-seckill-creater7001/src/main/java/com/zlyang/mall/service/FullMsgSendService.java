package com.zlyang.mall.service;

import com.zlyang.mall.entities.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zlyang
 * @date: 2021-12-07 14:22
 * @description:
 */
@Slf4j
@EnableBinding(Source.class)
public class FullMsgSendService {

    @Resource
    private MessageChannel output;

    private ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

    public boolean isFull(int seckillId){
        return concurrentHashMap.containsKey(seckillId);
    }

    public int sendFullMsg(int seckillId){
        if(concurrentHashMap.containsKey(seckillId)){
            return ResultMsgEnum.SUCCESS.getCode();
        }
        log.info("send full for" + seckillId);
        boolean send = output.send(MessageBuilder.withPayload(seckillId).build());
        concurrentHashMap.put(seckillId, "send");
        if(send){
            return ResultMsgEnum.SUCCESS.getCode();
        } else {
            return ResultMsgEnum.FAIL.getCode();
        }
    }
}
