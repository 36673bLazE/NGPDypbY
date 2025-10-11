// 代码生成时间: 2025-10-11 22:48:33
package com.b2b.purchasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class B2BPurchasingSystem {

    // REST API to list all products
    @GetMapping("/products")
    public ResponseEntity getAllProducts() {
        // Mockup data
        return ResponseEntity.ok("List of all products");
    }

    // REST API to get a single product by id
    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id") String productId) {
        // Mockup data
        return ResponseEntity.ok("Product with id: " + productId);
    }

    // REST API to add a new product
    @PostMapping("/products")
    public ResponseEntity addProduct(@RequestBody String productDetails) {
        // Mockup data
        return ResponseEntity.ok("Product added: " + productDetails);
    }

    // Exception handler for handling any exceptions that occur
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(B2BPurchasingSystem.class, args);
    }
}