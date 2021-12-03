package com.zlyang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlyang.mall.entities.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:42
 * @description:
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
