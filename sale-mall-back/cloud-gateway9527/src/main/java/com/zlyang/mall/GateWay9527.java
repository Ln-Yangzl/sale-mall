package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-04 15:55
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWay9527 {
    public static void main(String[] args) {
        SpringApplication.run(GateWay9527.class, args);
    }
}
