package com.example.GlovoDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@Table("product")
public class ProductEntity {
    @Id
    private int productId;
    private String name;
    private BigDecimal cost;
    private int order;
}
