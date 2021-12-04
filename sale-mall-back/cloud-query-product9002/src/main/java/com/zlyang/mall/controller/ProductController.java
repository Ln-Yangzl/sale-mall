package com.zlyang.mall.controller;

import com.zlyang.mall.entities.CommonResult;
import com.zlyang.mall.entities.Product;
import com.zlyang.mall.entities.ResultMsgEnum;
import com.zlyang.mall.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

    @PostMapping("/product/create")
    public CommonResult createProduct(@RequestBody Product product, @RequestBody MultipartFile pic){
        int status = 0;
        try {
            status = productService.createProduct(product, pic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(status > 0){
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
