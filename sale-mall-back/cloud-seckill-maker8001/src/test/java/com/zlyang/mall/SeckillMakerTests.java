package com.zlyang.mall;

import com.zlyang.mall.service.SeckillRestrictorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-06 16:46
 * @description:
 */
@SpringBootTest
public class SeckillMakerTests {
    @Resource
    private SeckillRestrictorService seckillRestrictorService;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Resource
    private RedisTemplate<String, Object> globalLockRedisTemplate;

    @Resource
    private ValueOperations<String, Object> globalLockValueOperations;

    @Test
    void testRedis(){
        valueOperations.set("test","testKey");
        Object test = valueOperations.get("test");
        System.out.println(test);
    }

    @Test
    void testRedisGlobalLock(){
        ValueOperations<String, Object> stringObjectValueOperations = globalLockRedisTemplate.opsForValue();
        stringObjectValueOperations.set("test", "test2");
        System.out.println(stringObjectValueOperations.get("test"));
        globalLockValueOperations.set("test3", "test3");
        System.out.println(globalLockValueOperations.get("test3"));
    }
}
