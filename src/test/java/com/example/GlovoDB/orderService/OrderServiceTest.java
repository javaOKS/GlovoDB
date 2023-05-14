package com.example.GlovoDB.orderService;

import com.example.GlovoDB.entity.OrderEntity;
import com.example.GlovoDB.entity.ProductEntity;
import com.example.GlovoDB.model.Order;
import com.example.GlovoDB.model.Product;
import com.example.GlovoDB.repository.OrderRepository;
import com.example.GlovoDB.repository.ProductRepository;
import com.example.GlovoDB.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;


public class OrderServiceTest {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        orderRepository = Mockito.mock(OrderRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
    }
    @Test
    public void getOrder(){
        List<ProductEntity> products = List.of(ProductEntity.builder().productId(1).name("banana").cost(new BigDecimal("2"))
                .order(1).build(),
                ProductEntity.builder().productId(2).name("apple").cost(new BigDecimal("1")).order(1).build());
        List<Product> productList = List.of(Product.builder().productId(1).name("banana").cost(new BigDecimal("2")).build(),
                Product.builder().productId(2).name("apple").cost(new BigDecimal("1")).build());
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(1)
                .date(Date.valueOf("2023-12-22"))
                .orderCost(new BigDecimal("3"))
                .build();
        Order expected = Order.builder()
                .orderId(1)
                .date(Date.valueOf("2023-12-22"))
                .orderCost(new BigDecimal("3"))
                .products(productList)
                .build();
        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(orderEntity));
        Mockito.when(productRepository.findAll()).thenReturn(products);
        OrderService orderService = new OrderService(orderRepository,productRepository);
        Order order = orderService.get(orderEntity.getOrderId());
        Assertions.assertEquals(expected,order);
    }
    @Test
    public void save() throws RuntimeException{
        List<Product> productList = List.of(
                Product.builder().productId(1).name("apple").cost(new BigDecimal("1")).build(),
                Product.builder().productId(2).name("banana").cost(new BigDecimal("2")).build());
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(1)
                .date(Date.valueOf("2023-12-22"))
                .orderCost(new BigDecimal("3"))
                .build();
        Order order = Order.builder()
                .orderId(1)
                .date(Date.valueOf("2023-12-22"))
                .orderCost(new BigDecimal("3"))
                .products(productList)
                .build();
        OrderService orderService1 = Mockito.spy(new OrderService(orderRepository,productRepository));
        Mockito.when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);
        Mockito.doNothing().when(orderService1).saveProduct(Mockito.any(ProductEntity.class));

        OrderService orderService = new OrderService(orderRepository,productRepository);
        orderService.save(order);

        Mockito.verify(orderRepository,Mockito.times(1)).save(Mockito.any(OrderEntity.class));
        Mockito.verify(productRepository,Mockito.times(2)).save(Mockito.any(ProductEntity.class));
    }
    @Test
    public void saveProduct(){
        ProductEntity product = ProductEntity.builder().productId(1).name("banana").cost(new BigDecimal("2")).build();

        OrderService orderService = new OrderService(orderRepository,productRepository);
        orderService.saveProduct(product);
        Mockito.verify(productRepository,Mockito.times(1)).save(Mockito.any(ProductEntity.class));
    }

    @Test
    public void delete(){
        Order order = Order.builder()
                .orderId(1).build();
        OrderService orderService = new OrderService(orderRepository,productRepository);
        orderService.delete(order.getOrderId());
        Mockito.verify(orderRepository,times(1)).deleteById(order.getOrderId());
    }

}
