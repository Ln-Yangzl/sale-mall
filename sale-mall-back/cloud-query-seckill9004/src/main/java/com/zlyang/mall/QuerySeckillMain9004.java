package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:58
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class QuerySeckillMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(QuerySeckillMain9004.class, args);
    }
}
