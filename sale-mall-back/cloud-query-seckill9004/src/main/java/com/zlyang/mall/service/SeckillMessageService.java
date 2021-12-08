package com.zlyang.mall.service;

import com.zlyang.mall.entities.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: zlyang
 * @date: 2021-12-08 13:26
 * @description:
 */
@Slf4j
@EnableBinding(Source.class)
public class SeckillMessageService {

    @Resource
    private MessageChannel output;

    public ResultMsgEnum sendSeckillRestrictionMsg(Integer seckillId, Integer amount, String expire){
        HashMap<String, String> msgPayload = new HashMap<>(3);
        msgPayload.put("seckillId", seckillId.toString());
        msgPayload.put("amount", amount.toString());
        msgPayload.put("expire", expire);
        boolean send = output.send(MessageBuilder.withPayload(msgPayload).build());
        if(send){
            return ResultMsgEnum.SUCCESS;
        } else {
            return ResultMsgEnum.FAIL;
        }
    }

}
