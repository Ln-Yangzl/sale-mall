package com.zlyang.mall.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: zlyang
 * @date: 2021-12-08 14:14
 * @description:
 */
public interface SeckillSink {
    String FULL_MSG = "fullMsgInput";
    String CREATE_MSG = "seckillCreateMsgInput";

    @Input(FULL_MSG)
    SubscribableChannel fullMsgInput();

    @Input(CREATE_MSG)
    SubscribableChannel seckillCreateMsgInput();
}
