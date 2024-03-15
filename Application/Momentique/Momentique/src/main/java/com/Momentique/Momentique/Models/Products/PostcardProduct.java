package com.Momentique.Momentique.Models.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PostcardProduct {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postcardId;
    private String title;
    private int price;


    public long getPostcardId() {
        return postcardId;
    }
    public void setPostcardId(long postcardId) {
        this.postcardId = postcardId;
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
