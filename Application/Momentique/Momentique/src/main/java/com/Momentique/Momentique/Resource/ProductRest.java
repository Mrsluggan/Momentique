package com.Momentique.Momentique.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Product;
import com.Momentique.Momentique.Repositories.ProductRepository;

@RestController

public class ProductRest {

    private final ProductRepository productRepository;

    public ProductRest(ProductRepository productRepository) {
        this.productRepository = productRepository;
        // Lägg till realistisk mockdata direkt i konstruktorn
        Product product1 = new Product();
        product1.setTitle("Smartphone");
        product1.setDescription("A high-end smartphone with all the latest features.");
        product1.setPrice(799);

        Product product2 = new Product();
        product2.setTitle("Laptop");
        product2.setDescription("A powerful laptop for work and entertainment.");
        product2.setPrice(1299);

        Product product3 = new Product();
        product3.setTitle("Headphones");
        product3.setDescription("Wireless headphones with noise cancellation technology.");
        product3.setPrice(199);

        // Lägg till produkterna i repository
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @GetMapping("products")
    public Iterable<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

}
