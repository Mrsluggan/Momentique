package com.Momentique.Momentique.Resource;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Orders;
import com.Momentique.Momentique.Models.Product;
import com.Momentique.Momentique.Repositories.OrderRepository;
import com.Momentique.Momentique.Repositories.ProductRepository;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.model.LineItemCollection;

@RestController
@CrossOrigin(origins = "*")
public class OrdersRest {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrdersRest(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("orders")
    public Iterable<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    // Skapa order, denna är WIP. Vi måste lista ut hur vi ska ta orden fron front till oss
    @GetMapping("orders/newOrder")
    public Orders createOrder() {
        orderRepository.deleteAll();
        Orders order = new Orders();
        Iterable<Product> products = productRepository.findAll();
        long totalCost = 0;
        for (Product product : products) {
            System.out.println(product.getPrice());
            totalCost += product.getPrice();
            order.setTotalCost(totalCost);
            order.getProducts().add(product);
        }

        orderRepository.save(order);

        return order;
    }

    // Hitta order

    @GetMapping("orders/{orderId}")
    public ResponseEntity<Optional<Orders>> findOrderById(@PathVariable("orderId") Long orderId) {
        Optional<Orders> result = orderRepository.findById(orderId);
        if (result != null) {
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.status(404).build();

    }

    // Ta bort order



    @GetMapping("success/{CHECKOUT_SESSION_ID}")
    public String getCust(@PathVariable("CHECKOUT_SESSION_ID") String checkoutId ) throws StripeException {
        System.out.println(checkoutId);
        Stripe.apiKey = "sk_test_51OoiqDF3bq9e58MhuJUSymuIlR3IVHXrzUIbxxPyNUdmLb2bLOjiYluaRajx1oFHivrdhWKUkJVBe6hcKUVWFkQr00jHP39g4d";

        Session session = Session.retrieve(checkoutId);
        System.out.println(session);
        LineItemCollection lineItems = session.listLineItems();
        System.out.println(lineItems);
        String str = "hej";
        return str;
    }

}
