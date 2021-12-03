package com.zlyang.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:42
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QueryProductMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(QueryProductMain9002.class, args);
    }
}
