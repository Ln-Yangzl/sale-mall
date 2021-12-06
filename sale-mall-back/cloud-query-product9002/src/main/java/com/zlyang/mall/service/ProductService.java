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
        // TODO:开启全局事物支持
        Product product = new Product(seckillProductDetail);
        Seckill seckill = new Seckill(seckillProductDetail);
        int productStatus = createProduct(product);
        seckill.setProductId(product.getProductId());
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
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return StatusEnum.FAIL.getCode();
        }
        return StatusEnum.SUCCESS.getCode();
    }

    public List<Product> getProductsInIds(List<Integer> ids){
        QueryWrapper<Product> queryWrapper = Wrappers.query();
        queryWrapper.in("product_id", ids);
        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }

    public int deleteProductById(Integer productId){
        int status = productMapper.deleteById(productId);
        if(status > 0){
            return StatusEnum.SUCCESS.getCode();
        } else {
            return StatusEnum.DATABASE_OPERATION_FAILED.getCode();
        }
    }

    public int deleteProductAndSeckillById(Integer productId, Integer seckillId){
        int productDeleteStatus = deleteProductById(productId);
        CommonResult seckillDeleteResult = seckillFeignService.deleteSeckillById(seckillId);
        if(seckillDeleteResult.getCode() == ResultMsgEnum.SUCCESS.getCode() && productDeleteStatus == StatusEnum.SUCCESS.getCode()){
            return StatusEnum.SUCCESS.getCode();
        }
        return StatusEnum.DATABASE_OPERATION_FAILED.getCode();
    }
}
