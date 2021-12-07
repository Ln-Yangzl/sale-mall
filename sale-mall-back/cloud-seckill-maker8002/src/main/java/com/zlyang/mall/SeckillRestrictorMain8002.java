package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-07 15:31
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeckillRestrictorMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(SeckillRestrictorMain8002.class, args);
    }
}
