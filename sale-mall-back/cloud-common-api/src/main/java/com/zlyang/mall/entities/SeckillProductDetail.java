package com.zlyang.mall.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zlyang
 * @date: 2021-12-04 14:35
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeckillProductDetail implements Serializable {
    private Integer seckillId;
    private Integer productId;
    private String title;
    private String disc;
    private String pic;
    private Integer inventory;
    private Integer price;
    private String startTime;
    private String endTime;

     public SeckillProductDetail(Product product, Seckill seckill){
        if(!product.getProductId().equals(seckill.getProductId())){
            throw new IllegalArgumentException("product and seckill id not equal! get:"
                    + product.toString() + "\n"
                    + seckill.toString());
        }
        seckillId = seckill.getSeckillId();
        productId = product.getProductId();
        title = product.getTitle();
        disc = product.getDisc();
        pic = product.getPic();
        price = product.getPrice();
        inventory = seckill.getSeckillInventory();
        startTime = seckill.getStartTime();
        endTime = seckill.getEndTime();
    }

    public static List<SeckillProductDetail> mergeProductsAndSeckills(List<Product> products, List<Seckill> seckills){
        List<SeckillProductDetail> result = seckills.stream().map(item -> {
            Product product = products.stream()
                    .filter(p -> p.getProductId().equals(item.getProductId()))
                    .findAny()
                    .orElse(null);
            return new SeckillProductDetail(product, item);
        }).collect(Collectors.toList());
        return result;
    }
}
