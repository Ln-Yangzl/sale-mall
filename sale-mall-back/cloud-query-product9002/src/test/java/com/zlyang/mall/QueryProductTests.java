package com.zlyang.mall;

import com.zlyang.mall.entities.Product;
import com.zlyang.mall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zlyang
 * @date: 2021-12-04 14:23
 * @description:
 */
@SpringBootTest
public class QueryProductTests {

    @Resource
    private ProductService productService;

    @Test
    void testSelectInIds(){
        ArrayList<Integer> list = new ArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
        List<Product> productsInIds = productService.getProductsInIds(list);
        productsInIds.forEach(System.out::println);
    }

    @Test
    void saveFileTransactionTest(){
        Product product = new Product();
        product.setTitle("testTransaction");
        try {
            productService.createProduct(product);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void nullPointerExceptionTest(){
        Product productById = productService.getProductById(4);
        System.out.println(productById);
    }
}
