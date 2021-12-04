package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.Product;
import com.zlyang.mall.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Product> getProductsInIds(List<Integer> ids){
        QueryWrapper<Product> queryWrapper = Wrappers.query();
        queryWrapper.in("product_id", ids);
        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }
}
