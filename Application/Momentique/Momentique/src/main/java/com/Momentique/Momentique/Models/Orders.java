package com.Momentique.Momentique.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String stripeOrderId;
    Long totalCost;

    Long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setOrder(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setOrder(null);
    }

    

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStripeOrderId() {
        return stripeOrderId;
    }

    public void setStripeOrderId(String stripeOrderId) {
        this.stripeOrderId = stripeOrderId;
    }

}
