package com.Momentique.Momentique.Models.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DigitalProduct {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long DigitalId;

    private String title;
    private int price;
    
    public long getDigitalId() {
        return DigitalId;
    }

    public void setDigitalId(long digitalId) {
        DigitalId = digitalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
}
