package com.Momentique.Momentique.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Orders;
import com.Momentique.Momentique.Repositories.OrderRepository;

@RestController
public class OrdersRest {

    private final OrderRepository orderRepository;

    public OrdersRest(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("orders")
    public Iterable<Orders> findAllOrders(){
        return orderRepository.findAll();
    }


    
}
