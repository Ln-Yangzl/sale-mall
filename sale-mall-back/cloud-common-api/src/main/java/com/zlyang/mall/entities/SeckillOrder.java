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
 * @date: 2021-12-03 17:37
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeckillOrder implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer productId;
    private Integer userId;
    private Integer amount;
    private String serial;
}
