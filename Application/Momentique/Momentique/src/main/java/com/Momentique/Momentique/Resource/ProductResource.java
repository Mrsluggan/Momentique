package com.Momentique.Momentique.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Product;
import com.Momentique.Momentique.Repositories.ProductRepository;

@RestController
public class ProductResource {

    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("products")
    public Iterable<Product> findAllProducts(){
        return this.productRepository.findAll();
    }



}
