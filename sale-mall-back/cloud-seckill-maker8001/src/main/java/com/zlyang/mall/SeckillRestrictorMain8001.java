package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-06 15:48
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeckillRestrictorMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(SeckillRestrictorMain8001.class, args);
    }
}
