package com.zlyang.mall.service;

import com.zlyang.mall.entities.Product;
import com.zlyang.mall.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:43
 * @description:
 */
@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    public Product getProductById(Integer id){
        return productMapper.selectById(id);
    }

    public int createProduct(Product product){
        return productMapper.insert(product);
    }
}
