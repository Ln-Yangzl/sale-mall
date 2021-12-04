package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.Product;
import com.zlyang.mall.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:43
 * @description:
 */
@Service
public class ProductService {

    @Value("${file-path}")
    private String FILE_PATH;

    @Resource
    private ProductMapper productMapper;

    public Product getProductById(Integer id){
        return productMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int createProduct(Product product, MultipartFile pic) throws IOException {
//        System.out.println(FILE_PATH);
        int insertStatus = productMapper.insert(product);
        String fileName = product.getProductId().toString() + pic.getOriginalFilename();
        File dest = new File(FILE_PATH + fileName);
//        System.out.println(dest);
        pic.transferTo(dest);
        return insertStatus;
    }

    public List<Product> getProductsInIds(List<Integer> ids){
        QueryWrapper<Product> queryWrapper = Wrappers.query();
        queryWrapper.in("product_id", ids);
        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }
}
