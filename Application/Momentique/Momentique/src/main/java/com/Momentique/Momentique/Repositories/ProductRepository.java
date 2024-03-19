package com.Momentique.Momentique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Momentique.Momentique.Models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.title LIKE %:title%")
    List<Product> searchProductByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM t_products WHERE product_Id <= 18 ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Product> selectedThreeRandom();


    @Query("SELECT p FROM Product p WHERE p.productId <= 18")
    List<Product>  searchProducts();
    
}