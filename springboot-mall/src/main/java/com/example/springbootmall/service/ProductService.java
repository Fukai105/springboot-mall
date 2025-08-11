package com.example.springbootmall.service;

import com.example.springbootmall.model.Product;
import org.springframework.stereotype.Component;


public interface ProductService  {
   Product getProductById(Integer productId);

}
