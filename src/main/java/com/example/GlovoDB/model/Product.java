package com.example.GlovoDB.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@Builder
public class Product {
    private int productId;
    private String name;
    private BigDecimal cost;

}
