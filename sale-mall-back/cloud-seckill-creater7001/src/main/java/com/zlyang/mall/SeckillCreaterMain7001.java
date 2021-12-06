package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zlyang
 * @date: 2021-12-06 14:29
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SeckillCreaterMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(SeckillCreaterMain7001.class, args);
    }
}
