package com.zlyang.mall.service;

import com.zlyang.mall.entities.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zlyang
 * @date: 2021-12-06 15:49
 * @description:
 */
@Slf4j
@EnableBinding(Source.class)
public class SeckillRestrictorService {

    @Resource
    private MessageChannel output;

    public int sendSeckillMessage(Integer seckillId, Integer userId, Integer amount){
        HashMap<String, Integer> args = new HashMap<>(3);
        args.put("seckillId", seckillId);
        args.put("userId", userId);
        args.put("amount", amount);
        log.info("message send: " + seckillId + " " + userId + " " + amount);
        boolean send = output.send(MessageBuilder.withPayload(args).build());
        if(send){
            return ResultMsgEnum.SUCCESS.getCode();
        } else {
            return ResultMsgEnum.FAIL.getCode();
        }

    }

    public int sendString(){
        output.send(MessageBuilder.withPayload("测试").build());
        return 0;
    }

}
