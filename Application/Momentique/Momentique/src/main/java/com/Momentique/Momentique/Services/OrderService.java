package com.Momentique.Momentique.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Momentique.Momentique.Repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // public Orders createAndSaveOrder(String stripeOrderId, Long totalCost, List<Product> products) {
    //     Orders order = new Orders();
    //     order.setStripeOrderId(stripeOrderId);
    //     order.setTotalCost(totalCost);
    //     order.setProducts(products);
    //     return orderRepository.save(order);
    // }

    // public Orders createAndSaveOrder(String stripeOrderId, Long totalCost, List<Product> products) {
    //     Orders order = new Orders();
    //     order.setStripeOrderId(stripeOrderId);
    //     order.setTotalCost(totalCost);

    //     // Koppla produkterna till ordern och hantera båda sidorna av relationen
    //     for (Product product : products) {
    //         order.addProduct(product);
    //     }

    //     return orderRepository.save(order);
    // }

}
