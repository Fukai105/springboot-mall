package com.example.springbootmall.model;

import com.example.springbootmall.constant.ProductCategory;

import java.util.Date;

public class Product {
    private Integer productId;
    private String productName;
    private ProductCategory category;
    private String imageUrl;
    private Integer Price;
    private Integer Stock;
    private String Description;
    private Date CreateDate;
    private Date lastModifiedDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
