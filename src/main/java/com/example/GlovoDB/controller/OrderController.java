package com.example.GlovoDB.controller;

import com.example.GlovoDB.model.Order;
import com.example.GlovoDB.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public Order get(@PathVariable Integer id){
        return this.orderService.get(id);
    }
    @PostMapping()
    public void saveOrder(@RequestBody Order order) {
        this.orderService.save(order);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id){
        this.orderService.delete(id);
    }

}
