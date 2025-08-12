package com.example.springbootmall.dao.impl;

import com.example.springbootmall.constant.ProductCategory;
import com.example.springbootmall.dao.ProductDao;
import com.example.springbootmall.dto.ProductRequest;
import com.example.springbootmall.model.Product;
import com.example.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductCategory category,String search) {
        String  sql = "select * from product where 1=1";

        Map<String, Object> map = new HashMap<>();

        if(category!=null){
            sql += " and category= :category";
            map.put("category", category.name());
        }

        if(search!=null){
            sql += " and product_name like :search";
            map.put("search","%"+search+"%");
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {

        String sql = """
            SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date
            FROM product
            WHERE product_id = :product_id
            """;

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql ="INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES(:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date date = new Date();
        map.put("created_date", date);
        map.put("last_modified_date", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        Integer productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId,ProductRequest productRequest) {
        String sql="UPDATE product set product_name=:product_name,category= :category,image_url= :image_url,price= :price,stock= :stock,description= :description,last_modified_date= :last_modified_date where product_id=:product_id";

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date date = new  Date();
        map.put("last_modified_date", date);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id=:product_id";

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);
        namedParameterJdbcTemplate.update(sql,map);
    }
}
