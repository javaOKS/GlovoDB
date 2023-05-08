package com.example.GlovoDB.converter;

import com.example.GlovoDB.entity.OrderEntity;
import com.example.GlovoDB.model.Order;

public class OrderConverter {
    public static Order orderEntityToOrder(OrderEntity orderEntity){
        return Order.builder()
                .orderId(orderEntity.getOrderId())
                .date(orderEntity.getDate())
                .orderCost(orderEntity.getOrderCost())
                .build();
    }
    public static OrderEntity orderToOrderEntity(Order order){
        return OrderEntity.builder()
                .orderId(order.getOrderId())
                .date(order.getDate())
                .orderCost(order.getOrderCost())
                .build();
    }

}

