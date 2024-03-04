package com.Momentique.Momentique.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.Momentique.Momentique.Models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    
}