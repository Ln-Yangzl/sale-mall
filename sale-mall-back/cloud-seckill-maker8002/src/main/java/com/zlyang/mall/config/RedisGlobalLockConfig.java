package com.zlyang.mall.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author: zlyang
 * @date: 2021-12-08 16:19
 * @description:
 */
@Configuration
public class RedisGlobalLockConfig {

    @Value("${spring.redis-global-lock.host}")
    private String host;

    @Value("${spring.redis-global-lock.port}")
    private Integer port;

    @Value("${spring.redis-global-lock.password}")
    private String password;

    @Value("${spring.redis-global-lock.database}")
    private Integer database;

    private static final int MAX_IDLE = 50; //最大空闲连接数
    private static final int MIN_IDLE = 1;
    private static final int MAX_TOTAL = 50; //最大连接数
    private static final long MAX_WAIT_MILLIS = 10000; //建立连接最长等待时间
    private static final int TIME_OUT = 60000;


    public RedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setPort(port);
        GenericObjectPoolConfig genericObjectPoolConfig =
                new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(MAX_IDLE);
        genericObjectPoolConfig.setMaxTotal(MAX_TOTAL);
        genericObjectPoolConfig.setMinIdle(MIN_IDLE);
        genericObjectPoolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder =  LettucePoolingClientConfiguration.builder().
                commandTimeout(Duration.ofMillis(TIME_OUT));
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();
        //根据配置和客户端配置创建连接
        LettuceConnectionFactory lettuceConnectionFactory = new
                LettuceConnectionFactory(redisStandaloneConfiguration,lettuceClientConfiguration);
        lettuceConnectionFactory .afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Bean(name = "globalLockRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(){
        RedisConnectionFactory redisConnectionFactory = connectionFactory();
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;
    }

    @Bean(name = "globalLockValueOperations")
    public ValueOperations<String, Object> valueOperations(@Qualifier("globalLockRedisTemplate") RedisTemplate<String, Object> globalLockRedisTemplate) {
        return globalLockRedisTemplate.opsForValue();
    }

}
