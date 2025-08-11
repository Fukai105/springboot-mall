package com.example.springbootmall.dao;

import com.example.springbootmall.dto.ProductRequest;
import com.example.springbootmall.model.Product;
import org.springframework.stereotype.Component;


public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
