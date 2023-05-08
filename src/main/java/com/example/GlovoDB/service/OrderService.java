package com.example.GlovoDB.service;

import com.example.GlovoDB.converter.OrderConverter;
import com.example.GlovoDB.converter.ProductConverter;
import com.example.GlovoDB.entity.OrderEntity;
import com.example.GlovoDB.entity.ProductEntity;
import com.example.GlovoDB.model.Order;
import com.example.GlovoDB.model.Product;
import com.example.GlovoDB.repository.OrderRepository;
import com.example.GlovoDB.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public void save(Order order){
        ProductEntity product;
        List<Product> products = order.getProducts();
        OrderEntity orderEntity = orderRepository.save(OrderConverter.orderToOrderEntity(order));
        for (Product p:products) {
            product = (ProductConverter.productToProductEntity(p));
            product.setOrder((orderEntity.getOrderId()));
            productRepository.save(product);
        }
    }
    public Order get(int id){
      OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        Order order = OrderConverter.orderEntityToOrder(orderEntity);
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(s -> s.getOrder() == id)
                .map(ProductConverter::productEntityToProduct)
                .collect(Collectors.toList());
        order.setProducts(products);
        return order;
    }
    public void delete(int id){
        orderRepository.deleteById(id);
    }

}
