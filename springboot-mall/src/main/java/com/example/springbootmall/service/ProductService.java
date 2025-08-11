package com.example.springbootmall.service;

import com.example.springbootmall.dto.ProductRequest;
import com.example.springbootmall.model.Product;
import org.springframework.stereotype.Component;


public interface ProductService  {
   Product getProductById(Integer productId);

   Integer createProduct(ProductRequest productRequest);

   void updateProduct(Integer productId,ProductRequest productRequest);

}
