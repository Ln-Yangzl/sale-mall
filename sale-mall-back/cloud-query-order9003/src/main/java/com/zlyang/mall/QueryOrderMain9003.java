package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-03 18:16
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QueryOrderMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(QueryOrderMain9003.class, args);
    }
}
