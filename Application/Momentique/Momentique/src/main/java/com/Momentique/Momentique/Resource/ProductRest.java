package com.Momentique.Momentique.Resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.Product;
import com.Momentique.Momentique.Repositories.ProductRepository;

import jakarta.websocket.server.PathParam;

@RestController

public class ProductRest {

    private final ProductRepository productRepository;

    public ProductRest(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping("products")
    public ResponseEntity<Iterable<Product>> findAllProducts() {

        // Bara för testing
        productRepository.deleteAll();
        List<Product> mockProducts = generateMockProducts();
        Iterable<Product> restult = productRepository.saveAll(mockProducts);

        // --------------

        return ResponseEntity.ok(restult);
    }

    @GetMapping("products/search/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
        System.out.println("Du sökte efter: " + title);

        if (title.length() < 3) {
            String errorMessage = "sökningen måste vara längre än 3 tecken.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        Iterable<Product> result = productRepository.searchProductByTitle(title);
        if (!result.iterator().hasNext()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);

    }

    private List<Product> generateMockProducts() {
        List<Product> products = new ArrayList<>();

        Product product3 = new Product();
        product3.setTitle("Laptop");
        product3.setDescription("High-performance laptop with SSD storage.");
        product3.setPrice(1200);
        products.add(product3);

        Product product4 = new Product();
        product4.setTitle("Smartphone");
        product4.setDescription("Latest smartphone model with advanced camera features.");
        product4.setPrice(800);
        products.add(product4);

        Product product5 = new Product();
        product5.setTitle("Wireless Headphones");
        product5.setDescription("Noise-cancelling wireless headphones for immersive audio experience.");
        product5.setPrice(250);
        products.add(product5);

        Product product6 = new Product();
        product6.setTitle("Fitness Tracker");
        product6.setDescription("Waterproof fitness tracker with heart rate monitor and GPS.");
        product6.setPrice(100);
        products.add(product6);

        Product product7 = new Product();
        product7.setTitle("Coffee Maker");
        product7.setDescription("Programmable coffee maker with built-in grinder.");
        product7.setPrice(150);
        products.add(product7);

        Product product8 = new Product();
        product8.setTitle("Yoga Mat");
        product8.setDescription("Eco-friendly yoga mat with non-slip surface.");
        product8.setPrice(50);
        products.add(product8);

        Product product9 = new Product();
        product9.setTitle("External Hard Drive");
        product9.setDescription("Portable external hard drive with terabyte storage capacity.");
        product9.setPrice(80);
        products.add(product9);

        Product product10 = new Product();
        product10.setTitle("Wireless Mouse");
        product10.setDescription("Ergonomic wireless mouse with customizable buttons.");
        product10.setPrice(40);
        products.add(product10);

        Product product11 = new Product();
        product11.setTitle("Portable Bluetooth Speaker");
        product11.setDescription("Compact Bluetooth speaker with long battery life.");
        product11.setPrice(70);
        products.add(product11);

        Product product12 = new Product();
        product12.setTitle("Backpack");
        product12.setDescription("Durable backpack with padded laptop compartment.");
        product12.setPrice(60);
        products.add(product12);

        return products;
    }

}
