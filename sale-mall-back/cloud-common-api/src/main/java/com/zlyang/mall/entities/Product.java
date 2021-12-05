package com.zlyang.mall.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:35
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer productId;
    private String title;
    private Integer inventory;
    private Integer price;
    private String disc;
    private String pic;

    public Product(SeckillProductDetail seckillProductDetail){
        productId = seckillProductDetail.getProductId();
        title = seckillProductDetail.getTitle();
        inventory = seckillProductDetail.getInventory();
        price = seckillProductDetail.getPrice();
        disc = seckillProductDetail.getDisc();
        pic = seckillProductDetail.getPic();
    }
}
