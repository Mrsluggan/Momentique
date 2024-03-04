package com.Momentique.Momentique.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.Momentique.Momentique.Models.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    
}