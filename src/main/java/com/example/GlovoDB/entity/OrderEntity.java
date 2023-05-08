package com.example.GlovoDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@Table("order")
public class OrderEntity {
    @Id
    private int orderId;
    private Date date;
    private BigDecimal orderCost;
}
