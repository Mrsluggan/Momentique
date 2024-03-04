package com.Momentique.Momentique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Momentique.Momentique.Models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.title LIKE %:title%")
    List<Product> searchProductByTitle(@Param("title") String title);

}