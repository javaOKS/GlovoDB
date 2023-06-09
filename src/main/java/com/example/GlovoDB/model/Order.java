package com.example.GlovoDB.model;

import com.example.GlovoDB.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class Order {
    private int orderId;
    private Date date;
    private BigDecimal orderCost;
    private List<Product> products;
}
