package com.zlyang.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zlyang.mall.entities.*;
import com.zlyang.mall.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    @Value("${pic-pref}")
    private String PIC_PREF;

    @Resource
    private ProductMapper productMapper;

    @Resource SeckillFeignService seckillFeignService;

    public Product getProductById(Integer id){
        return productMapper.selectById(id);
    }

    public int createProductAndSeckill(SeckillProductDetail seckillProductDetail){
        Product product = new Product(seckillProductDetail);
        Seckill seckill = new Seckill(seckillProductDetail);
        int productStatus = createProduct(product);
        seckill.setProductId(product.getProductId());
        System.out.println(product.getProductId());
        CommonResult commonResult = seckillFeignService.createSeckill(seckill);
        int status = 0;
        if(productStatus <= 0 || commonResult.getCode() != ResultMsgEnum.SUCCESS.getCode()){
            status = 1;
        }
        return status;
    }

    public int createProduct(Product product){
        product.setPic(PIC_PREF + product.getPic());
        return productMapper.insert(product);
    }

    public int saveFile(MultipartFile file){
        String fileName = FILE_PATH + file.getOriginalFilename();
        File dest = new File(fileName);
        int status = 0;
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            status = 1;
        }
        return status;
    }

    public List<Product> getProductsInIds(List<Integer> ids){
        QueryWrapper<Product> queryWrapper = Wrappers.query();
        queryWrapper.in("product_id", ids);
        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }
}
