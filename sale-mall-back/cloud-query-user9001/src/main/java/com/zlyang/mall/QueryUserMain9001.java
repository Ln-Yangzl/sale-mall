package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-03 16:03
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QueryUserMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(QueryUserMain9001.class, args);
    }
}
