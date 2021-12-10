package com.zlyang.mall.service;

import com.zlyang.mall.entities.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: zlyang
 * @date: 2021-12-06 15:49
 * @description:
 */
@Slf4j
@EnableBinding(Source.class)
public class SeckillRestrictorService {

    private static final int USER_LOCK_SECONDS = 10;

    @Resource
    private MessageChannel output;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Resource
    private ValueOperations<String, Object> globalLockValueOperations;

    public ResultMsgEnum sendSeckillMessage(Integer seckillId, Integer userId, Integer amount, String serial){
        if(!globalLockValueOperations.setIfAbsent(userId.toString(), "lock", USER_LOCK_SECONDS, TimeUnit.SECONDS)){
            log.info("Blocked by redis global lock for user: " + userId);
            return ResultMsgEnum.ACCESS_VIOLATION;
        }
        if(redisTemplate.hasKey(seckillId.toString() + "lock")){
            log.info("Blocked by redis for lock: " + seckillId);
            return ResultMsgEnum.SECKILL_FULL;
        }
        if(valueOperations.decrement(seckillId.toString() + "count") < 0){
            log.info("Blocked by redis for count: " + seckillId);
            return ResultMsgEnum.SECKILL_FULL;
        }
        HashMap<String, String> args = new HashMap<>(3);
        args.put("seckillId", seckillId.toString());
        args.put("userId", userId.toString());
        args.put("amount", amount.toString());
        args.put("serial", serial);
        log.info("message send: " + seckillId + " " + userId + " " + amount + " " + serial);
        boolean send = output.send(MessageBuilder.withPayload(args).build());
        if(send){
            return ResultMsgEnum.SUCCESS;
        } else {
            return ResultMsgEnum.FAIL;
        }

    }

}
