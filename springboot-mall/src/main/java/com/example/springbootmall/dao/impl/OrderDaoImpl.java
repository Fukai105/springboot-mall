package com.example.springbootmall.dao.impl;

import com.example.springbootmall.dao.OrderDao;
import com.example.springbootmall.model.Order;
import com.example.springbootmall.model.OrderItem;
import com.example.springbootmall.rowmapper.OrderItemRowMapper;
import com.example.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.repository.query.ParametersSource;
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
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select * from `order` where order_id= :orderId";

        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        if(orderList.size()>0){
            return orderList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemByOrderItemsById(Integer orderId) {
        String sql = "select * from order_item as oi LEFT JOIN product as p ON oi.product_id=p.product_id where oi.order_id=:orderId";

        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql,map,new OrderItemRowMapper());

        return orderItemList;
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {

        String sql = "INSERT INTO `order` (user_id,total_amount,created_date,last_modified_date) VALUES (:userId,:totalAmount,:createdDate,:lastModifiedDate)";

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("totalAmount",totalAmount);

        Date date = new Date();
        map.put("createdDate",date);
        map.put("lastModifiedDate",date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int orderId = keyHolder.getKey().intValue();
        return orderId;

    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {

        String sql="INSERT INTO order_item (order_id,product_id,quantity,amount) VALUES ( :orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] mapSqlParameterSource=new MapSqlParameterSource[orderItemList.size()];

        for(int i=0;i<orderItemList.size();i++){
            OrderItem orderItem=orderItemList.get(i);

            mapSqlParameterSource[i]=new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("orderId",orderId);
            mapSqlParameterSource[i].addValue("productId",orderItem.getProductId());
            mapSqlParameterSource[i].addValue("quantity",orderItem.getQuantity());
            mapSqlParameterSource[i].addValue("amount",orderItem.getAmount());

        }
        namedParameterJdbcTemplate.batchUpdate(sql,mapSqlParameterSource);
    }
}
