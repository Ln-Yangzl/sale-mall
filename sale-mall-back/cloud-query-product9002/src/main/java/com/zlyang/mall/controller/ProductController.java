package com.zlyang.mall.controller;

import com.zlyang.mall.entities.*;
import com.zlyang.mall.service.ProductService;
import com.zlyang.mall.service.SeckillFeignService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-03 17:45
 * @description:
 */
@RestController
public class ProductController {

    @Resource
    private ProductService productService;


    @GetMapping("/product/get")
    public CommonResult getProductById(@RequestParam(name = "productId") Integer id){
        Product product = productService.getProductById(id);
        return CommonResult.success(product);
    }

    @PostMapping("/product/savePic")
    public CommonResult savePic(@RequestBody MultipartFile picFile){
        System.out.println(picFile);
        int i = productService.saveFile(picFile);
        if(i == 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.FAIL);
        }
    }

    @PostMapping("/product/createProductAndSeckill")
    public CommonResult createProduct(@RequestBody SeckillProductDetail seckillProductDetail){
        System.out.println(seckillProductDetail);
        int status = productService.createProductAndSeckill(seckillProductDetail);
        if(status == 0){
            return CommonResult.success(null);
        } else {
            return CommonResult.error(ResultMsgEnum.DATABASE_OPERATION_FAILED);
        }
    }

    @PostMapping("/product/selectInIds")
    public CommonResult selectInIds(@RequestBody List<Integer> ids){
        List<Product> products = productService.getProductsInIds(ids);
        return CommonResult.success(products);
    }
}
